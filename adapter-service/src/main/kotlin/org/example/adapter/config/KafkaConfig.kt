package org.example.adapter.config

import org.springframework.cloud.stream.config.*
import org.springframework.context.annotation.*
import org.springframework.kafka.annotation.*
import org.springframework.kafka.listener.*
import org.springframework.util.backoff.*

@Configuration
@EnableKafka
class KafkaConfig {

    @Bean
    fun listenerContainerCustomizer(
        applicationConsumerProperties: ApplicationConsumerProperties
    ): ListenerContainerCustomizer<AbstractMessageListenerContainer<String?, String?>>? {
        val errorHandler = SeekToCurrentErrorHandler(
            FixedBackOff(
                applicationConsumerProperties.interval,
                applicationConsumerProperties.maxAttempts
            )
        )
        return ListenerContainerCustomizer { container: AbstractMessageListenerContainer<String?, String?>, _: String?, _: String? ->
            container.setErrorHandler(
                errorHandler
            )
        }
    }
}