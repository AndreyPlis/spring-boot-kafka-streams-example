package org.example.adapter.config

import com.fasterxml.jackson.databind.*
import org.springframework.context.annotation.*


@Configuration
class CloudStreamConfiguration(private val objectMapper: ObjectMapper) {

}