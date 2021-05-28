package com.rafaelbarbieru.erbapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rafaelbarbieru.erbapi.controllers"))
                .paths(PathSelectors.any())
                .build();
//                .apiInfo(getApiInfo());
    }

//    private ApiInfo getApiInfo() {
//        return new ApiInfo(
//                "ERB API",
//                "Epic Rap Battles of History API",
//                "1.0",
//                "https://google.com",
//                new Contact("Rafael Barbieru", "https://google.com", "rc.barbieru@gmail.com"),
//                "LICENSE",
//                "LICENSE URL",
//                Collections.emptyList()
//        );
//    }
}
