package com.tistory.kmmoon.payment.adaptor.`in`

import com.tistory.kmmoon.payment.PaymentEntity
import com.tistory.kmmoon.payment.application.PaymentQueryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payments")
class PaymentController (
  val paymentService: PaymentQueryService
) {

  @GetMapping("/all", produces = ["application/json"])
  fun findAll(): ResponseEntity<List<PaymentEntity>> {
    return ResponseEntity
      .ok()
      .body(paymentService.findAll())
  }
}