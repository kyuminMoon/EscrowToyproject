package com.tistory.kmmoon.order.adaptor.`in`.web

import com.tistory.kmmoon.order.OrderEntity
import com.tistory.kmmoon.order.application.OrderCreateService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user/orders")
class OrderController (
  val orderService: OrderCreateService
) {

  @GetMapping("/all", produces = ["application/json"])
  fun findAll(): ResponseEntity<List<OrderEntity>> {
    return ResponseEntity
      .ok()
      .body(orderService.findAll())
  }
}