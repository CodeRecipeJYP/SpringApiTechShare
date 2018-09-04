package com.navercorp.bookserver;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zalando.problem.ProblemModule;

@SpringBootApplication
public class BookServerApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(BookServerApplication.class, args);
    }

    @Bean
    public com.fasterxml.jackson.databind.Module jdk8Module() {
        return new Jdk8Module();
    }

    @Bean
    public com.fasterxml.jackson.databind.Module problemModule() {
        return new ProblemModule();
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .favorParameter(true)
                .parameterName("format");
    }
}
