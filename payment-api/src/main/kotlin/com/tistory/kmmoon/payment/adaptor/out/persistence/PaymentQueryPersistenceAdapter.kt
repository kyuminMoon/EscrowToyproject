package com.tistory.kmmoon.payment.adaptor.out.persistence

import com.tistory.kmmoon.payment.PaymentRepository
import com.tistory.kmmoon.payment.application.port.out.QueryPaymentPort
import org.springframework.stereotype.Component

@Component
class PaymentQueryPersistenceAdapter(
    val paymentRepository: PaymentRepository
) : QueryPaymentPort {

}