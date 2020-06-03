package com.xwl.otherserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.exception.ExceptionEnum;
import com.xwl.comserver.utils.R;
import com.xwl.otherserver.domain.Query;
import com.xwl.otherserver.domain.UserInfo;
import com.xwl.otherserver.service.UserService;
import com.xwl.otherserver.utils.MyPage;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
     * 新增或修改
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
}
