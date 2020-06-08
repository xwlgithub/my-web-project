package com.xwl.otherserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.exception.ExceptionEnum;
import com.xwl.comserver.utils.JwtUtils;
import com.xwl.comserver.utils.R;
import com.xwl.otherserver.dto.MenuPermissDto;
import com.xwl.otherserver.vo.Query;
import com.xwl.otherserver.domain.UserInfo;
import com.xwl.otherserver.service.UserService;
import com.xwl.otherserver.utils.MyPage;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 薛
 * @Date: 2020/5/29 15:59
 * @Description:
 */
@RestController
@RequestMapping("/userList")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    @GetMapping("findUserList")
    public R<MyPage<UserInfo>> findUserList(Query query, String userName) {
        MyPage<UserInfo> infoMyPage = userService.fidUserList(query, userName);
        return R.data(infoMyPage);
    }

    /**
     * 新增或修改/注册
     *
     * @param userJson
     * @return
     */
    @PostMapping("saveUserByParams")
    public R<Boolean> saveUserByParams(@RequestBody String userJson) {
        Object userObject = JSONObject.parseObject(userJson).get("userJson");
        UserInfo user = JSONObject.toJavaObject((JSON) userObject, UserInfo.class);
        if (StringUtils.isEmpty(user)) {
            return R.errors(ExceptionEnum.MUST_PARAM_IS_NOT_NULL);
        }
        if (!StringUtils.isEmpty(user.getIsReset())) {
            if (!user.getPassword().equals(user.getAgainPsd())) {
                return R.errors(ExceptionEnum.PSD_IS_NOT_LIKE);
            }
        }
        try {
            userService.saveUserByParams(user);
        } catch (ApiException e) {
            return R.errors(e.getExceptionEnum());
        }
        return R.data(true);
    }

    /**
     * 删除用户-ID
     *
     * @param id
     * @return
     */
    @PostMapping("deleteUserById/{id}")
    public R<Boolean> deleteUserById(@PathVariable("id") Long id) {
        if (StringUtils.isEmpty(id)) {
            return R.errors(ExceptionEnum.MUST_PARAM_IS_NOT_NULL);
        }
        Boolean isSuccess = null;
        try {
            isSuccess = userService.deleteUserById(id);
        } catch (ApiException e) {
            return R.errors(e.getExceptionEnum());
        }
        return R.data(isSuccess);
    }

    /**
     * 登录-成功-签发token令牌
     * * @param userInfo
     *
     * @return
     */
    @PostMapping("loginServer")
    public R<Map<String, Object>> loginServer(@RequestBody @Valid UserInfo userInfo, BindingResult bindingResult) {
        Map<String, Object> map=new HashMap<>();
        //校验数据是否满足规定格式
        if (bindingResult.hasErrors()) {
            //校验结果以集合的形式返回
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            Integer index = (int) (Math.random() * fieldErrorList.size());
            return R.fail(fieldErrorList.get(index).getDefaultMessage());
        }
        UserInfo use=null;
        try {
            use=userService.loginServer(userInfo);
        } catch (ApiException e) {
           return R.errors(e.getExceptionEnum());
        }
        map.put("access_token", JwtUtils.createTokenByParams(use.getName(),use.getEmailAddress()));
        return R.data(map);
    }
}
