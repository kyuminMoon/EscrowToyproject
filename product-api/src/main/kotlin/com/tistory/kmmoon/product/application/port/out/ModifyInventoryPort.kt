package com.tistory.kmmoon.product.application.port.out

import com.tistory.kmmoon.product.InventoryEntity

interface ModifyInventoryPort {
    fun modify(entity: InventoryEntity): InventoryEntity
}