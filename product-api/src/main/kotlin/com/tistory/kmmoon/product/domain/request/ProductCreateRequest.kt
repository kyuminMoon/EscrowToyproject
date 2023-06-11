package com.tistory.kmmoon.product.domain.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size

data class ProductCreateRequest(
  @Size(min = 2, max = 50)
  val name: String,
  @Size(min = 2, max = 1000)
  val description: String,
  @Min(100)
  @Max(100_000_000)
  val price: Int,
  @Min(1)
  @Max(10_000)
  val quantity: Int,
) {
  init {
    println("test")
  }

//  constructor(name: String, description: String, price: BigDecimal, quantity: Int): this(name, description, price, quantity) {
//    println("name $name, description $description, price $price, quantity $quantity")
//  }
}