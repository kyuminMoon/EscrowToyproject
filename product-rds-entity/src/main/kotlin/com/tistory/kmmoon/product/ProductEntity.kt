package com.tistory.kmmoon.product

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "products")
data class ProductEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  val productId: Long,

  val name: String,
  val description: String,
  val price: BigDecimal,
  val stock: Int,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
)