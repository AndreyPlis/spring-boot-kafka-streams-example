package org.example.adapter.operation.controller

import com.fasterxml.jackson.databind.*
import org.example.adapter.operation.service.*
import org.example.adapter.operation.validator.*
import org.example.external.event.*
import org.example.external.exception.*
import org.springframework.http.*
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(value = ["/shipment/v1"])
class ShipmentAdvancedNotificationController(
    private val validationService: ValidationService,
    private val objectMapper: ObjectMapper,
    private val shipmentAdvancedNotificationService: ShipmentAdvancedNotificationService
) {

    @PostMapping(value = ["/process"], produces = [MediaType.APPLICATION_JSON_VALUE])
    open fun processShipment(@RequestBody event: String) {

        try {
            val message = objectMapper.readValue(event, ShipmentEvent::class.java)
            if (!validationService.isValid(message)) {
                throw ShipmentDataProcessingException(message)
            }
            shipmentAdvancedNotificationService.process(message)
        } catch (e: ShipmentDataProcessingException) {
            throw e
        } catch (exception: Exception) {
            throw  ShipmentDataProcessingException(exception)
        }


    }


    @ExceptionHandler(ShipmentDataProcessingException::class)
    fun handleException(e: ShipmentDataProcessingException): ResponseEntity<ShipmentEventResponse> {
        val shipmentEventResponse = ShipmentEventResponse(e.event?.id, objectMapper.writeValueAsString(ShipmentEventResponseDto(e.message)))
        return ResponseEntity<ShipmentEventResponse>(shipmentEventResponse, HttpStatus.BAD_REQUEST)
    }
}