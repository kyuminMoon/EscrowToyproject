package com.tistory.kmmoon.product.domain.request

import java.math.BigDecimal

data class ProductCreateRequest(
  val name: String,
  val description: String,
  val price: BigDecimal,
  val quantity: Int,
)