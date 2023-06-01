package com.tistory.kmmoon.product.application.port.out

interface DeleteProductPort {
    fun delete(productId: Long)
}