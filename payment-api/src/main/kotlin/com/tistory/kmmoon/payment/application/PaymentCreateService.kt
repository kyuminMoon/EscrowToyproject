package com.tistory.kmmoon.payment.application

import com.tistory.kmmoon.payment.PaymentEntity
import com.tistory.kmmoon.payment.PaymentRepository
import com.tistory.kmmoon.payment.application.port.`in`.PaymentCreateUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PaymentCreateService: PaymentCreateUseCase {

  @Autowired
  lateinit var paymentRepository: PaymentRepository;


  fun findAll(): List<PaymentEntity>? {
    return paymentRepository.findAllBy();
  }

}