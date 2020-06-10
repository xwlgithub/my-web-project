package com.xwl.zuulserver.config;

import com.xwl.comserver.exception.ExceptionEnum;
import com.xwl.comserver.utils.R;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;


/**
 * @Auther: 薛
 * @Date: 2020/6/10 16:16
 * @Description:过滤器拦截filter抛出异常
 */
//@RestController
//@Component
public class FilterErrorConfig extends BasicErrorController {

    public FilterErrorConfig(ErrorAttributes errorAttributes) {
        super(errorAttributes, new ErrorProperties());
    }
    // 重写 error 方法
    @RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public R errors(HttpServletRequest request) {
        ExceptionEnum e=(ExceptionEnum)request.getAttribute("TOKEN_ERROR");

        return R.errors(e);
    }
}
