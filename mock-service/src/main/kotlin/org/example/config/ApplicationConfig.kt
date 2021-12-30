package org.example.config

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.module.kotlin.*
import org.jeasy.random.*
import org.springframework.beans.factory.annotation.*
import org.springframework.context.annotation.*

@Configuration
open class ApplicationConfig {

    @Bean
    open fun easyRandom(): EasyRandom {
        val easyRandomParameters = EasyRandomParameters()
        easyRandomParameters.collectionSizeRange(1,1)
        return EasyRandom(easyRandomParameters)
    }


    @Autowired
    fun registerKotlinMapper(objectMapper: ObjectMapper)
    {
        objectMapper.registerKotlinModule().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
    }
}