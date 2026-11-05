package com.example.fraudruleengine;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bankaiCoreAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("BANKAI Core")
                                .description("Financial Transaction Intelligence, Fraud Detection and Risk Analysis Platform")
                .version("1.0")
                );
    }
}
