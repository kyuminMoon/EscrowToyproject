package com.tistory.kmmoon.payment.adaptor.`in`

import com.tistory.kmmoon.core.security.UserSecurity
import com.tistory.kmmoon.payment.PaymentEntity
import com.tistory.kmmoon.payment.application.PaymentQueryService
import com.tistory.kmmoon.payment.application.port.`in`.PaymentCreateUseCase
import com.tistory.kmmoon.payment.application.port.`in`.PaymentDeleteUseCase
import com.tistory.kmmoon.payment.application.port.`in`.PaymentModifyUseCase
import com.tistory.kmmoon.payment.application.port.`in`.PaymentQueryUseCase
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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