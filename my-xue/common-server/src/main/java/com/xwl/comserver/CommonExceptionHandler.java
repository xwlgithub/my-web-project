package com.xwl.comserver;

import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.utils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Auther: 薛
 * @Date: 2020/6/3 09:17
 * @Description:
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public R filterApiException(ApiException api){
        return R.errors(api.getExceptionEnum());
    }

}
