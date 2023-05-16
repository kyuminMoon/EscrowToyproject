package com.tistory.kmmoon.payment.adaptor.`in`

import com.tistory.kmmoon.payment.PaymentEntity
import com.tistory.kmmoon.payment.application.PaymentCreateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentController {

  @Autowired
  private lateinit var paymentService: PaymentCreateService

  @GetMapping("/all", produces = ["application/json"])
  fun findAll(): ResponseEntity<List<PaymentEntity>> {
    return ResponseEntity
      .ok()
      .body(paymentService.findAll())
  }
}