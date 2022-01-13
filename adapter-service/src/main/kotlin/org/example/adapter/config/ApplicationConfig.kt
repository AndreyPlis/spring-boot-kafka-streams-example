package org.example.adapter.config

import org.springframework.context.annotation.*
import org.springframework.context.annotation.Configuration
import javax.validation.*

@Configuration
class ApplicationConfig {

    @Bean
    fun validator(): Validator = Validation.buildDefaultValidatorFactory().usingContext().validator


}