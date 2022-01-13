package org.example.adapter

import java.time.*

data class AdapterAsnDto (val data: LocalDateTime, val number:String, val member:String, val orders:List<AdapterOrderDto>)