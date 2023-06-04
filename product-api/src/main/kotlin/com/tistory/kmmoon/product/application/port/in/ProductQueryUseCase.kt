package com.tistory.kmmoon.product.application.port.`in`

import com.tistory.kmmoon.product.domain.Product
import com.tistory.kmmoon.product.domain.request.ProductCreateRequest
import com.tistory.kmmoon.product.domain.request.ProductModifyRequest

interface ProductQueryUseCase {
  fun findAll(): List<Product>?
  fun findProductInfo(id: Long): Product?
  fun create(userId: Long, productCreateRequest: ProductCreateRequest): Product?
  fun modify(productModifyRequest: ProductModifyRequest): Product?
  fun delete(productId: Long, userId: Long)
}