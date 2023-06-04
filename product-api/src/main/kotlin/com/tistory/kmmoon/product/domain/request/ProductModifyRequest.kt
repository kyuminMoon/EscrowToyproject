package com.tistory.kmmoon.product.domain.request

import java.math.BigDecimal

data class ProductModifyRequest(
    var id: Long,
    var userId: Long,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val stock: Int,
)