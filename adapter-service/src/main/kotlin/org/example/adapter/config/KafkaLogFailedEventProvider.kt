package org.example.adapter.config

import org.springframework.kafka.support.serializer.*
import java.nio.charset.*
import java.util.function.Function

class KafkaLogFailedEventProvider: Function<FailedDeserializationInfo, String> {
    override fun apply(t: FailedDeserializationInfo): String {
        return String(t.data, StandardCharsets.UTF_8)
    }
}