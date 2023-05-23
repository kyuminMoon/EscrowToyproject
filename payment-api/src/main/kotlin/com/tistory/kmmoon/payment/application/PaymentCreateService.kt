package com.tistory.kmmoon.payment.application

import com.tistory.kmmoon.payment.PaymentEntity
import com.tistory.kmmoon.payment.PaymentRepository
import com.tistory.kmmoon.payment.application.port.`in`.PaymentCreateUseCase
import org.springframework.stereotype.Service

@Service
class PaymentCreateService (
  var paymentRepository: PaymentRepository
) : PaymentCreateUseCase {

  fun findAll(): List<PaymentEntity>? {
    return paymentRepository.findAllBy();
  }

}