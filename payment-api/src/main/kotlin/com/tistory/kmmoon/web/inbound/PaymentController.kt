package com.tistory.kmmoon.web.inbound

import com.tistory.kmmoon.payment.PaymentEntity
import com.tistory.kmmoon.service.PaymentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentController {

  @Autowired
  private lateinit var paymentService: PaymentService

  @GetMapping("/all", produces = ["application/json"])
  fun findAll(): ResponseEntity<List<PaymentEntity>> {
    return ResponseEntity
      .ok()
      .body(paymentService.findAll())
  }
}