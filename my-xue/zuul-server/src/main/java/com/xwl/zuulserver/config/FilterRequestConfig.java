package com.xwl.zuulserver.config;

import com.xwl.comserver.exception.ExceptionEnum;

import org.apache.commons.lang.StringUtils;
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
public class FilterRequestConfig implements Filter {
    //统一token参数
    private static final String TOCKEN = "access_tocken";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //转换请求request
        HttpServletRequest requestHttp = (HttpServletRequest) request;
        //OPTIONS特殊请求过滤
        if (requestHttp.getMethod().equals("OPTIONS")) {
            chain.doFilter(request, response);
            return;
        }
        //权限认证部分请求不通过鉴权(如:登录接口,获取token接口,异常返回接口等)
        System.out.println("url"+requestHttp.getRequestURI());
        if (requestHttp.getRequestURI().equals("/login/loginServers")
                || requestHttp.getRequestURI().equals("/api/other-server/userList/findAuthByToken")
                || requestHttp.getRequestURI().equals("/login/401")
                /* filterMap.put("/swagger-ui.html", "anon");
                 filterMap.put("/swagger-resources/**", "anon");
                 filterMap.put("/v2/**", "anon");
                 filterMap.put("/webjars/**", "anon");*/
                || requestHttp.getRequestURI().equals("/swagger-ui.html")
                || requestHttp.getRequestURI().contains("/swagger-resources")
                || requestHttp.getRequestURI().contains("/v2")
                || requestHttp.getRequestURI().contains("/webjars")
                || requestHttp.getRequestURI().contains("/doc.html")
                ) {
                chain.doFilter(request, response);
                return;
            }
        //获取请求token
        String authTocken = requestHttp.getHeader(TOCKEN);
        //如果为空即未鉴权||授权 返回异常信息
        if (StringUtils.isEmpty(authTocken)) {
            requestHttp.setAttribute("TOKEN_ERROR", ExceptionEnum.AUTH_NO_AUTHENTION);
            requestHttp.getRequestDispatcher("/login/401").forward(requestHttp, response);
        }
        //放行~
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
