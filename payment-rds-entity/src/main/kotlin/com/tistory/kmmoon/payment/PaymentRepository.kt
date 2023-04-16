package com.tistory.kmmoon.payment

import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<PaymentEntity, Long> {
  fun findAllBy(): List<PaymentEntity>
}