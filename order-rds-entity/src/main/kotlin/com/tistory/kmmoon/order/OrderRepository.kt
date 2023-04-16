package com.tistory.kmmoon.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<OrderEntity, Long> {
  fun findAllBy(): List<OrderEntity>

}