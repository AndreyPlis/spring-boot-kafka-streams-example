package org.example.service

import org.example.external.*
import org.jeasy.random.*
import org.springframework.beans.factory.annotation.*
import org.springframework.cloud.stream.function.*
import org.springframework.stereotype.*


@Service
class ShipmentAdvancedNotification(
    streamBridge: StreamBridge,
    @Value("\${app.kafka.binders.shipment-advanced-notification-binder-name}") binderName: String,
    easyRandom: EasyRandom,
) : AbstractRandomProducer<AsnDto>(
    streamBridge,
    binderName,
    easyRandom,
) {

    override fun createRandomData(): AsnDto =
        easyRandom.nextObject(AsnDto::class.java)
}