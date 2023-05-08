package com.tistory.kmmoon.product.application.port.out

import com.tistory.kmmoon.product.ProductEntity

interface CreateProductPort {
  fun create(entity: ProductEntity): ProductEntity
}