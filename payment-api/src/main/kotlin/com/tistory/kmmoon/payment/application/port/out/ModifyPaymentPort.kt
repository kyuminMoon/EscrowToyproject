package com.tistory.kmmoon.payment.application.port.out

import com.tistory.kmmoon.payment.PaymentEntity

interface ModifyPaymentPort {
    fun modify(entity: PaymentEntity): PaymentEntity
}
