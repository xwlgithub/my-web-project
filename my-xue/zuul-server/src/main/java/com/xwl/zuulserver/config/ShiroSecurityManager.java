package com.xwl.zuulserver.config;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: 薛
 * @Date: 2020/6/10 13:39
 * @Description:shiro最外层过滤器工厂-拦截请求
 */
@Configuration
public class ShiroSecurityManager   {
    @Bean//交由spring容器管理
    public ShiroFilterFactoryBean shiroWebFilterConfig(){
        //新建shiro web过滤器工厂
        ShiroFilterFactoryBean shiroBean=new ShiroFilterFactoryBean();
        //创建securityManager环境
        shiroBean.setSecurityManager(securityManager());
        //配置拦截链 LinkedHashMap有序，添加顺序进行拦截
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问,先配置anon再配置authc。
        filterChainMap.put("/**", "anon");
        shiroBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroBean;
    }
    @Bean
    public SecurityManager securityManager() {
        //realm配置器支持
        UserRealm  userRealm=new UserRealm();
        //服务端使用WebSecuritManager构建环境
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //添加realm支持 认证授权
        securityManager.setRealm(userRealm);
        return securityManager;
    }
    //4.配置shiro注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
