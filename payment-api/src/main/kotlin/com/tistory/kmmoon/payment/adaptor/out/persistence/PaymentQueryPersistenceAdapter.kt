package com.tistory.kmmoon.payment.adaptor.out.persistence

import com.tistory.kmmoon.payment.PaymentEntity
import com.tistory.kmmoon.payment.PaymentRepository
import com.tistory.kmmoon.payment.application.port.out.QueryPaymentPort
import org.springframework.stereotype.Component

@Component
class PaymentQueryPersistenceAdapter(
    val paymentRepository: PaymentRepository
) : QueryPaymentPort {
    override fun findAll(): List<PaymentEntity> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Long): PaymentEntity {
        TODO("Not yet implemented")
    }

}