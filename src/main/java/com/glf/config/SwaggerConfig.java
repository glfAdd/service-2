package com.glf.config;

/*
swagger web: http://localhost:8080/doc.html




*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author glfadd
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        // 指定使用Swagger2规范
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(new ApiInfoBuilder()
                        // 描述字段支持Markdown语法
                        .description("# Knife4j RESTful APIs").termsOfServiceUrl("https://doc.xiaominfo.com/").contact(
                                "xiaoymin@foxmail.com").version("1.0").build())
                // 分组名称
                .groupName("用户服务").select()
                // 这里指定 Controller 扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.glf.controller")).paths(PathSelectors.any()).build();
    }
}
