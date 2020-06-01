package com.xwl.zuulserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 网关注入注册中心
 */
//Boot启动必须类
@SpringBootApplication
//开启网关转发服务
@EnableZuulProxy
//被注册中心发现
@EnableEurekaClient
@MapperScan("com.xwl.zuulserver.mapper")
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }

}
