package com.tistory.kmmoon.payment.domain.iamport.request

import java.math.BigDecimal
import java.time.LocalDateTime

data class PaymentCreateRequest(
    val amount: BigDecimal,
    val paymentMethod: String,
    val paymentStatus: String,
    val escrowStatus: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
