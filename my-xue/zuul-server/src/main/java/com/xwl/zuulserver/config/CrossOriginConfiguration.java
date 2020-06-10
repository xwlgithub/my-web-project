package com.xwl.zuulserver.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Auther: 薛
 * 过滤全部请求-交由Filter处理
 * @Date: 2020/4/23 13:58
 * @Description:
 */
//@Configuration
public class CrossOriginConfiguration extends WebMvcConfigurationSupport {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
