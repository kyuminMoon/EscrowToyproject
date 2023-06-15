package com.tistory.kmmoon.payment

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "payments")
data class PaymentEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  val id: Long,
  val amount: BigDecimal,
  val paymentMethod: String,
  val paymentStatus: String,
  val escrowStatus: String,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime
)