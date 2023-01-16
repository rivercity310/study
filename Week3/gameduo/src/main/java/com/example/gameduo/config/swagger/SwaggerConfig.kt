package com.example.gameduo.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
open class SwaggerConfig {
    @Bean
    open fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.gameduo.controller"))
            .build()
            .apiInfo(this.commonInfo())
    }

    private fun commonInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("게임듀오 기업과제")
            .description("게임듀오 API 개발")
            .version("v1")
            .contact(
                Contact(
                    "Seungsu",
                    "https://github.com/rivercity310",
                    "#"
                )
            )
            .build()
    }
}