package org.example.adapter

import java.time.*

data class AdapterOrderDto(val createdData: LocalDateTime, val number: String, val items: List<String>)
