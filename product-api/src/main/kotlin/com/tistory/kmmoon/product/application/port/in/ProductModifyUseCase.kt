package com.tistory.kmmoon.product.application.port.`in`

import com.tistory.kmmoon.product.domain.Product
import com.tistory.kmmoon.product.domain.request.ProductModifyRequest

interface ProductModifyUseCase {
    fun modify(productModifyRequest: ProductModifyRequest): Product
}