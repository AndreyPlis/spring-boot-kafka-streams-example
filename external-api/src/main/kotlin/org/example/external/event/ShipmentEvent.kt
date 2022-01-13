package org.example.external.event

import javax.validation.constraints.*


data class ShipmentEvent(
    @NotBlank var requestData:  String? = null,
    @NotBlank var id: String? = null) {
}