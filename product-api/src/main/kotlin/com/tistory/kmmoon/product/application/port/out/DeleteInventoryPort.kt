package com.tistory.kmmoon.product.application.port.out

interface DeleteInventoryPort {
    fun delete(inventoryId: Long)
}