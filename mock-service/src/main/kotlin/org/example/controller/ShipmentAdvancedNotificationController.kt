package org.example.controller

import org.example.external.type.*
import org.example.service.*
import org.springframework.web.bind.annotation.*
import javax.validation.*

@RestController
@RequestMapping("/shipment")
class ShipmentAdvancedNotificationController(private val shipmentAdvancedNotification: ShipmentAdvancedNotification) {

    @PostMapping("/random")
    fun sendRandomData() {
        shipmentAdvancedNotification.produceRandomData()
    }

    @PostMapping("/start")
    fun startProducingRandomData(@RequestParam timeout: Long, @RequestParam count: Int) {
        shipmentAdvancedNotification.startProducingRandomData(timeout, count)
    }

    @PostMapping("/stop")
    fun stopProducingRandomData() {
        shipmentAdvancedNotification.stopProducingRandomData()
    }

    @PostMapping("/data")
    fun sendData(@Valid @RequestBody asnDto: AsnDto) {
        shipmentAdvancedNotification.produce(asnDto)
    }
}