package com.xwl.showserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author xuewenliang
 * @Auther: 薛
 * @Date: 2020/7/7 10:15
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
/**
 * 开启feign接口调用
 */
@EnableFeignClients
public class ShowApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShowApplication.class,args);
    }
}
