package com.tistory.kmmoon.product.application.port.`in`

import com.tistory.kmmoon.product.domain.Product
import com.tistory.kmmoon.product.domain.request.ProductCreateRequest

interface ProductCreateUseCase {
  fun create(request: Long, productCreateRequest: ProductCreateRequest): Product
}