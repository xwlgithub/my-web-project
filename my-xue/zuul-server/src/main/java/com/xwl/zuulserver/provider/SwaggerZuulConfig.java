package com.xwl.zuulserver.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/7/9 17:16
 * @Description:
 */
@EnableSwagger2
@Configuration
@Primary //多个bean时 此类优先使用
public class SwaggerZuulConfig implements SwaggerResourcesProvider {
    //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    Boolean swaggerEnabled=true;

    @Autowired
    RouteLocator routeLocator;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled).select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.xwl.zuulserver"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build().pathMapping("/");
    }

    //设置api信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("骠骑撸码大都督API接口文档")
                .description("xuewenliang-gaoyating")
                .contact(new Contact("薛文良-高亚婷", "https://www.baidu.com", "未知~"))
                .version("1.0.0")
                .termsOfServiceUrl("https://www.baidu.com")
                .build();
    }

    @Override
    public List<SwaggerResource> get() {
        //利用routeLocator动态引入微服务
        List<SwaggerResource> resources = new ArrayList<>();
        resources.add(swaggerResource("网关服务","/v2/api-docs","1.0"));
        //循环 使用Lambda表达式简化代码
        System.out.println("~~~"+routeLocator.toString());
        routeLocator.getRoutes().forEach(route ->{
            //动态获取
            System.out.println("%%%%"+route.toString());
            resources.add(swaggerResource(route.getId(),route.getFullPath().replace("**", "v2/api-docs"), "1.0"));
        });
        return resources;
    }

    private SwaggerResource swaggerResource(String name,String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        System.out.println("名称"+name+"路径"+location+"版本"+version);
        String serverName="";
        if (name.equals("网关服务")){
            serverName="网关服务";
        }else if (name.equals("other")){
            serverName="系统服务";
        }else {
            serverName="业务服务";
        }
        swaggerResource.setName(serverName);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
