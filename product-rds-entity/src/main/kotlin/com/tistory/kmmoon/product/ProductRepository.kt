package com.tistory.kmmoon.product

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<ProductEntity, Long> {
  fun findAllBy(): List<ProductEntity>
}