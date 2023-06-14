package com.tistory.kmmoon.payment.application.port.out

import com.tistory.kmmoon.payment.PaymentEntity

interface QueryPaymentPort {
    fun findAll(): List<PaymentEntity>
}
