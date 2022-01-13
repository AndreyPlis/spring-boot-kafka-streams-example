package org.example.adapter.operation.service

import com.fasterxml.jackson.databind.*
import org.example.adapter.operation.mapper.*
import org.example.adapter.operation.validator.*
import org.example.external.event.*
import org.example.external.exception.*
import org.example.external.type.*
import org.springframework.beans.factory.annotation.*
import org.springframework.cloud.stream.function.*
import org.springframework.stereotype.*

@Service
class ShipmentAdvancedNotificationService(
    private val validationService: ValidationService,
    private val objectMapper: ObjectMapper,
    @Value("\${app.output.agreement.binding}")
    private var binderName: String,
    private val streamBridge: StreamBridge,
    //private val mapper: EventMapper
) {
    fun process(shipmentEvent: ShipmentEvent) {
        val shipmentRequestDataDto: ShipmentRequestDataDto?
        try {
            shipmentRequestDataDto = objectMapper.readValue(
                shipmentEvent.requestData,
                ShipmentRequestDataDto::class.java
            )

            if (!validationService.isValid(shipmentRequestDataDto)) {
                throw ShipmentDataProcessingException(shipmentEvent)
            }

            shipmentRequestDataDto.ans.forEach{
                streamBridge.send(binderName,it)
            }
        } catch (e: Exception) {
            throw ShipmentDataProcessingException(e.message, e, shipmentEvent)
        }
    }
}