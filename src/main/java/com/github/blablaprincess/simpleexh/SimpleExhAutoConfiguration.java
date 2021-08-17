package com.github.blablaprincess.simpleexh;

import com.github.blablaprincess.simpleexh.errordtobuilder.DefaultErrorDtoBuilder;
import com.github.blablaprincess.simpleexh.errordtobuilder.ErrorDtoBuilder;
import com.github.blablaprincess.simpleexh.exceptioncoderesolver.DefaultExceptionCodeResolver;
import com.github.blablaprincess.simpleexh.exceptioncoderesolver.ExceptionCodeResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.github.blablaprincess.simpleexh")
@ConditionalOnWebApplication
public class SimpleExhAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    ExceptionCodeResolver defaultExceptionCodeResolver() {
        return new DefaultExceptionCodeResolver();
    }

    @Bean
    @ConditionalOnMissingBean
    ErrorDtoBuilder defaultErrorDtoBuilder() {
        return new DefaultErrorDtoBuilder();
    }
}
