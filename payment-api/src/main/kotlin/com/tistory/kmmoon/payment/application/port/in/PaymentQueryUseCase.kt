package com.tistory.kmmoon.payment.application.port.`in`

import com.tistory.kmmoon.payment.PaymentEntity
import org.springframework.data.domain.Pageable

interface PaymentQueryUseCase {
    fun findAll(pageable: Pageable): List<PaymentEntity>?
}
