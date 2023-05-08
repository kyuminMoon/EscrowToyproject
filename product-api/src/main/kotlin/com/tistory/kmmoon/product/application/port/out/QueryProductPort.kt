package com.tistory.kmmoon.product.application.port.out

import com.tistory.kmmoon.product.ProductEntity

interface QueryProductPort {
  fun findAllBy(): List<ProductEntity>?
  fun findById(id: Long): ProductEntity?
}