package com.tistory.kmmoon.order.adaptor.`in`

import com.tistory.kmmoon.order.OrderEntity
import com.tistory.kmmoon.order.application.OrderCreateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController {

  @Autowired
  private lateinit var orderService: OrderCreateService

  @GetMapping("/all", produces = ["application/json"])
  fun findAll(): ResponseEntity<List<OrderEntity>> {
    return ResponseEntity
      .ok()
      .body(orderService.findAll())
  }
}