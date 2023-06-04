package com.tistory.kmmoon.product

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<ProductEntity, Long> {
  fun findAllBy(): List<ProductEntity>
}