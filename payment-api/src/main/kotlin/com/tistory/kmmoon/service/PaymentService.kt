package com.tistory.kmmoon.service

import com.tistory.kmmoon.payment.PaymentEntity
import com.tistory.kmmoon.payment.PaymentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class PaymentService {

  @Autowired
  lateinit var paymentRepository: PaymentRepository;

  fun findAll(): List<PaymentEntity>? {
    return paymentRepository.findAllBy();
  }

}