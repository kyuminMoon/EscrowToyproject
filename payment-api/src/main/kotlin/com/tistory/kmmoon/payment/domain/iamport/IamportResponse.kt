package com.tistory.kmmoon.payment.domain.iamport

data class IamportResponse(
    val applyNum: String,
    val bankName: String,
    val buyerAddr: String,
    val buyerEmail: String,
    val buyerName: String,
    val buyerPostcode: String,
    val buyerTel: String,
    val cardName: String,
    val cardNumber: String,
    val cardQuota: Int,
    val currency: String,
    val customData: String,
    val impUid: String,
    val merchantUid: String,
    val name: String,
    val paidAmount: Int,
    val paidAt: Int,
    val payMethod: String,
    val pgProvider: PgProvider,
    val pgTid: String,
    val pgType: String,
    val receiptUrl: String,
    val status: String,
    val success: Boolean
)