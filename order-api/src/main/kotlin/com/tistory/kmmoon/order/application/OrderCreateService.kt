package com.tistory.kmmoon.order.application

import com.tistory.kmmoon.order.application.port.`in`.OrderCreateUseCase
import com.tistory.kmmoon.order.OrderEntity
import com.tistory.kmmoon.order.infrastructure.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderCreateService(
  val orderRepository: OrderRepository
) : OrderCreateUseCase {

  fun findAll(): List<OrderEntity>? {
    return orderRepository.findAllBy();
  }

}