package com.tistory.kmmoon.product

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InventoryRepository : JpaRepository<InventoryEntity, Long> {
  fun findAllBy(): List<InventoryEntity>
}