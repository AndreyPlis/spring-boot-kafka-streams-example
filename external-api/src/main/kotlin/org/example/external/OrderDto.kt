package org.example.external

import java.time.*

data class OrderDto(val createdData: LocalDateTime, val number: String, val items: List<String>)
