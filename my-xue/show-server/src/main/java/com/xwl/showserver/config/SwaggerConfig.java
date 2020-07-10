package com.xwl.showserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: 薛
 * @Date: 2020/7/9 17:07
 * @Description:
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    //是否开启swagger，，
    Boolean swaggerEnabled=true;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled).select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.xwl.showserver"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build().pathMapping("/");
    }

    //设置api信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("骠骑撸码大都督API接口文档")
                .description("整合就完事了~")
                .contact(new Contact("薛文良", "https://www.baidu.com", "未知~"))
                .version("1.0.0")
                .termsOfServiceUrl("https://www.baidu.com")
                .build();
    }
}
