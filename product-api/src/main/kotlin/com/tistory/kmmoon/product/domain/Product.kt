package com.tistory.kmmoon.product.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class Product(
  val productId: Long,
  val userId: Long,
  val name: String,
  val description: String,
  val price: BigDecimal,
  val stock: Int,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
)