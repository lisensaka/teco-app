package com.taco.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig{
        //implements WebMvcConfigurer


    @Bean
    public Docket productApi(){
        // ktu po percaktojme tipin qe do perdoret per dokumentimin e RestAPI-s
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // ktu po percaktojme paketen qe duhet te skanohet dhe te krijohet dokumentimi vetem per ato qe ndodhen tek ky path
                .apis(RequestHandlerSelectors.basePackage("com.taco"))
                //ktu po percaktojme se per cilen url do te krijohen kto dokumentime, pra mund te filtrojme dhe tek url qe ndodhen brenda paketave
                .paths(regex("/api.*"))
                .build();
    }
}
