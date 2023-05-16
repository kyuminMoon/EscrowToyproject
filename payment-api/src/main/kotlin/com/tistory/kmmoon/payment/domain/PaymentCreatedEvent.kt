package com.tistory.kmmoon.order.domain

data class OrderCreatedEvent(
    val orderId: String,
    val customerId: String,
    val totalPrice: Double,
    val items: List<OrderItemInfo>
)

data class OrderItemInfo(
    val productId: String,
    val quantity: Int,
    val price: Double
)