package com.example.demo.config;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig
{

    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                // 需要生成文档的包路径
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo()
    {
        // 文档的标题、描述、服务条款网址、版本、联系方式等信息
        return new ApiInfoBuilder().title("微博客户端API").description("微博客户端API操作文档")
                .termsOfServiceUrl("http://codelabx.com/").version("1.0").contact(new Contact("代码研究室", "http://codelabx.com/", "admin@codelabx.com")).build();
    }
}