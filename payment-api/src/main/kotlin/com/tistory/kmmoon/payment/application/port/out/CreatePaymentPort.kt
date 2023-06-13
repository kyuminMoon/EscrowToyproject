package com.tistory.kmmoon.payment.application.port.out

import com.tistory.kmmoon.payment.PaymentEntity

interface CreatePaymentPort {
    fun create(entity: PaymentEntity): PaymentEntity
}
