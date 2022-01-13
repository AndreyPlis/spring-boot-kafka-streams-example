package org.example.external.type

import java.time.*

data class AsnDto (val data:LocalDateTime, val number:String, val member:String, val orders:List<OrderDto>)