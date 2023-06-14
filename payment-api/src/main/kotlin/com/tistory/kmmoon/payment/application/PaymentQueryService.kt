package com.tistory.kmmoon.payment.application

import com.tistory.kmmoon.payment.PaymentEntity
import com.tistory.kmmoon.payment.application.port.`in`.PaymentCreateUseCase
import com.tistory.kmmoon.payment.application.port.out.QueryPaymentPort
import org.springframework.stereotype.Service

@Service
class PaymentQueryService (
  val queryPaymentPort: QueryPaymentPort
) : PaymentCreateUseCase {

  fun findAll(): List<PaymentEntity>? {
    return queryPaymentPort.findAll();
  }

}