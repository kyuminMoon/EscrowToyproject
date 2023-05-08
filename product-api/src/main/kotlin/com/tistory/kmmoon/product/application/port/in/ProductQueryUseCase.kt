package com.tistory.kmmoon.product.application.port.`in`

import com.tistory.kmmoon.product.domain.Product

interface ProductQueryUseCase {
  fun findAll(): List<Product>?
  fun findProductInfo(id: Long): Product?
}