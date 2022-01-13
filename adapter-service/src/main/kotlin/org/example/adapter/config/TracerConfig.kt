package org.example.adapter.config

import io.jaegertracing.internal.*
import io.opentracing.contrib.java.spring.jaeger.starter.*
import org.springframework.boot.autoconfigure.condition.*
import org.springframework.context.annotation.*

@Configuration
@ConditionalOnProperty(name = ["opentracing.jaeger.service-name"])
class TracerConfig {

    @Bean
    fun expandByMDCScopeManager(): ExpandByMDCScopeManager {
        return ExpandByMDCScopeManager()
    }

    class ExpandByMDCScopeManager : TracerBuilderCustomizer {
        override fun customize(builder: JaegerTracer.Builder) {
            val mdcScopeManager: MDCScopeManager = MDCScopeManager.Builder().build()
            builder.withScopeManager(mdcScopeManager)
        }
    }
}