package com.xwl.otherserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @Auther: 薛
 * @Date: 2020/5/29 15:54
 * @Description:
 */
@EnableFeignClients(basePackages = "com.xwl.otherserver")
@EnableDiscoveryClient
@SpringBootApplication
public class OtherServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OtherServerApplication.class,args);
    }
}
