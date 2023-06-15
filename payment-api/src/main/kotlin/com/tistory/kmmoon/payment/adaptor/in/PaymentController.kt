package com.tistory.kmmoon.payment.adaptor.`in`

import com.tistory.kmmoon.core.security.UserSecurity
import com.tistory.kmmoon.payment.application.port.`in`.PaymentCreateUseCase
import com.tistory.kmmoon.payment.application.port.`in`.PaymentDeleteUseCase
import com.tistory.kmmoon.payment.application.port.`in`.PaymentModifyUseCase
import com.tistory.kmmoon.payment.application.port.`in`.PaymentQueryUseCase
import com.tistory.kmmoon.payment.domain.iamport.request.Payment
import com.tistory.kmmoon.payment.domain.iamport.request.PaymentCreateRequest
import com.tistory.kmmoon.payment.domain.iamport.request.PaymentModifyRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/payments")
class PaymentController (
  val paymentQueryUseCase: PaymentQueryUseCase,
  val paymentCreateUseCase: PaymentCreateUseCase,
  val paymentModifyUseCase: PaymentModifyUseCase,
  val paymentDeleteUseCase: PaymentDeleteUseCase
) {

  @GetMapping("/all", produces = ["application/json"])
  fun findAll(pageable: Pageable): ResponseEntity<List<Payment>> {
    return ResponseEntity
      .ok()
      .body(paymentQueryUseCase.findAll(pageable))
  }

  @PostMapping(produces = ["application/json"])
  fun create(paymentCreateRequest: PaymentCreateRequest, @AuthenticationPrincipal userSecurity: UserSecurity): ResponseEntity<Payment> {
    return ResponseEntity
      .ok()
      .body(paymentCreateUseCase.create(paymentCreateRequest, userSecurity))
  }

  @PutMapping(name = "/{paymentId}", produces = ["application/json"])
  fun create(@PathVariable paymentId: Long, @AuthenticationPrincipal userSecurity: UserSecurity, paymentModifyRequest: PaymentModifyRequest): ResponseEntity<Payment> {
    paymentModifyRequest.id = paymentId
    return ResponseEntity
      .ok()
      .body(paymentModifyUseCase.modify(paymentModifyRequest, userSecurity))
  }

  @DeleteMapping(name = "/{paymentId}", produces = ["application/json"])
  fun create(@PathVariable paymentId: Long, @AuthenticationPrincipal userSecurity: UserSecurity): ResponseEntity<Payment> {
    return ResponseEntity
      .ok()
      .body(paymentDeleteUseCase.delete(paymentId, userSecurity))
  }
}