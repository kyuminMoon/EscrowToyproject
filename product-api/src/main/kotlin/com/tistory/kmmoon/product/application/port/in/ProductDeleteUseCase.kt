package com.tistory.kmmoon.product.application.port.`in`

interface ProductDeleteUseCase {
    fun delete(userId: Long, productId: Long)
}