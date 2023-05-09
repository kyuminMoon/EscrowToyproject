package com.tistory.kmmoon.order.domain.request

import java.math.BigDecimal
import java.time.LocalDateTime

data class OrderCreateRequest(
  val id: Long,
  val userId: Long,
  val status: String,
  val totalAmount: BigDecimal,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
  val orderItems: MutableList<OrderItem>,
  val paymentId: Long?
)

data class OrderItem(
  val id: Long,
  val quantity: Int,
  val price: BigDecimal,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime
)