package com.tistory.kmmoon.product.application.port.out

import com.tistory.kmmoon.product.ProductEntity

interface DeleteProductPort {
    fun delete(entity: ProductEntity)
}