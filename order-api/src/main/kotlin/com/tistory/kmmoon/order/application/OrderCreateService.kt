package com.tistory.kmmoon.order.application

import com.tistory.kmmoon.order.application.port.`in`.OrderCreateUseCase
import com.tistory.kmmoon.order.OrderEntity
import com.tistory.kmmoon.order.infrastructure.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrderCreateService: OrderCreateUseCase {

  @Autowired
  lateinit var orderRepository: OrderRepository;


  fun findAll(): List<OrderEntity>? {
    return orderRepository.findAllBy();
  }

}