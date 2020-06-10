package com.xwl.zuulserver.config;

import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.exception.ExceptionEnum;
import com.xwl.comserver.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Auther: 薛
 * @Date: 2020/6/10 15:34
 * @Description:拦截器
 */
@Component
public class FilterRequestConfig  implements Filter {
    private static final String TOCKEN = "access_tocken";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest requestHttp = (HttpServletRequest) request;
//        if (requestHttp.getMethod().equals("OPTIONS")){
//            chain.doFilter(request,response);
//            return;
//        }
//        System.out.println(requestHttp.getRequestURI());
//        if (requestHttp.getRequestURI().equals("/login/loginServers")||requestHttp.getRequestURI().equals("/api/other-server/userList/findAuthByToken")){
//            chain.doFilter(request,response);
//            return;
//        }
//        String authTocken = requestHttp.getHeader(TOCKEN);
//        Claims claims=null;
//        try {
//             claims = JwtUtils.parseClaimsByToken(authTocken);
//        } catch (ApiException e) {
//            ApiException apiException = new ApiException(e.getExceptionEnum());
//            requestHttp.setAttribute("TOKEN_ERROR",e.getExceptionEnum());
//            throw apiException;
//        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
