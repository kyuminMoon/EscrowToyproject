package com.tistory.kmmoon.payment.application.port.out

import com.tistory.kmmoon.payment.PaymentEntity

interface DeletePaymentPort {
    fun delete(entity: PaymentEntity): PaymentEntity
}
