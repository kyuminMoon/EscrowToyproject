package com.tistory.kmmoon.service

import com.tistory.kmmoon.order.OrderEntity
import com.tistory.kmmoon.order.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrderService {

  @Autowired
  lateinit var orderRepository: OrderRepository;

  fun findAll(): List<OrderEntity>? {
    return orderRepository.findAllBy();
  }

}