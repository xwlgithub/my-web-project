package com.xwl.zuulserver.controller;

import com.xwl.comserver.domain.UserInfo;
import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.exception.ExceptionEnum;
import com.xwl.comserver.utils.JwtUtils;
import com.xwl.comserver.utils.MdFivePsdUtils;
import com.xwl.comserver.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 薛
 * @Date: 2020/6/10 14:17
 * @Description:
 */
@RestController
@RequestMapping("login")
@Api(value = "登录接口",tags = "登录认证")
public class LoginController {
    @ApiOperation(value = "登录认证生成加密令牌",notes = "传入用户名&密码",position = 1)
    @ApiParam(value = "传入用户实例对象",name = "userInfo",required = true)
    @PostMapping("loginServers")
    public R<Map<String, Object>> loginServers(@RequestBody @Valid UserInfo userInfo, BindingResult bindingResult) {
        Map<String, Object> map = new HashMap<>();
        //校验数据是否满足规定格式
        if (bindingResult.hasErrors()) {
            //校验结果以集合的形式返回
            List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
            Integer index = (int) (Math.random() * fieldErrorList.size());
            return R.fail(fieldErrorList.get(index).getDefaultMessage());
        }
        //加密密码业务比对
        String mdPsd = MdFivePsdUtils.MD5(userInfo.getPassword());
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getName(),mdPsd);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return R.errors(ExceptionEnum.NAME_OR_PSD_ERROR);
        }catch (ApiException e){
            return R.errors(e.getExceptionEnum());
        }
        map.put("access_token", JwtUtils.createTokenByParams(userInfo.getName(), userInfo.getPassword()));
        return R.data(map);
    }

    /**
     * 统一返回认证失败信息接口
     * @param request
     * @return
     */
    @RequestMapping("401")
    public R responseStatus(HttpServletRequest request){
        ExceptionEnum token_error = null;
        try {
            token_error = (ExceptionEnum)request.getAttribute("TOKEN_ERROR");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.errors(token_error);
    }
}
