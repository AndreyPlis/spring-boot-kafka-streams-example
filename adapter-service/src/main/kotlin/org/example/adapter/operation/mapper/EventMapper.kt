package org.example.adapter.operation.mapper

import org.example.adapter.*
import org.example.external.type.*
import org.mapstruct.*


@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
interface EventMapper {

    fun toAsn(asnDto: AsnDto): AdapterAsnDto

    fun toOrder(orderDto: OrderDto): AdapterOrderDto
}