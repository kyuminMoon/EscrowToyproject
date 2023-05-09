package com.tistory.kmmoon.order.domain.mapper

import com.tistory.kmmoon.order.OrderEntity
import com.tistory.kmmoon.order.domain.Order
import com.tistory.kmmoon.order.domain.request.OrderCreateRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface OrderMapper {

  @Mapping(target = "id", ignore = true)
  fun createEntity(request: OrderCreateRequest): OrderEntity

  fun toData(entity: OrderEntity?): Order

  fun toData(findAllBy: List<OrderEntity>?): List<Order>?
}