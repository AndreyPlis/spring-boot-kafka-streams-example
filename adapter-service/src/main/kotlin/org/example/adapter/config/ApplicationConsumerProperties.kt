package org.example.adapter.config

import org.springframework.boot.context.properties.*
import org.springframework.context.annotation.*


@Configuration
@ConfigurationProperties("kafka.consumer.config")
class ApplicationConsumerProperties {

     val interval = 10000L
     val maxAttempts = 3L
     val dlt: Dlt = Dlt()


    class Dlt {
        private val enable = false
        private val topic = "dlt-topic"
        private val partition = 0
    }

}