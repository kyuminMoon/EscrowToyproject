package com.tistory.kmmoon.product

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "products")
data class ProductEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  var id: Long,
  var userId: Long,
  var name: String,
  var description: String,
  var price: BigDecimal,
  var active: Boolean = true,
  @CreationTimestamp
  var createdAt: LocalDateTime,
  @UpdateTimestamp
  var updatedAt: LocalDateTime,
)