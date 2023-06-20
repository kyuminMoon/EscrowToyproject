package com.tistory.kmmoon.payment.application

import com.tistory.kmmoon.payment.PaymentEntity
import com.tistory.kmmoon.payment.application.port.`in`.PaymentCreateUseCase
import com.tistory.kmmoon.payment.application.port.`in`.PaymentQueryUseCase
import com.tistory.kmmoon.payment.application.port.out.QueryPaymentPort
import com.tistory.kmmoon.payment.domain.iamport.request.Payment
import com.tistory.kmmoon.payment.domain.payment.mapper.PaymentMapper
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PaymentQueryService (
  val queryPaymentPort: QueryPaymentPort,
  val mapper: PaymentMapper
) : PaymentQueryUseCase {

  override fun findAll(pageable: Pageable): List<Payment>? {
    return mapper.toData(queryPaymentPort.findAll());
  }

}