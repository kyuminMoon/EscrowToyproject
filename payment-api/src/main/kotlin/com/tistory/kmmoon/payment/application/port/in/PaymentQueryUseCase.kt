package com.tistory.kmmoon.payment.application.port.`in`

import com.tistory.kmmoon.payment.PaymentEntity
import com.tistory.kmmoon.payment.domain.iamport.request.Payment
import org.springframework.data.domain.Pageable

interface PaymentQueryUseCase {
    fun findAll(pageable: Pageable): List<Payment>?
}
