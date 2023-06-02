package com.tistory.kmmoon.product.application.port.out

import com.tistory.kmmoon.product.ProductEntity

interface ModifyProductPort {
    fun modify(entity: ProductEntity): ProductEntity
}