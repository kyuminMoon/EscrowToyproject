package com.tistory.kmmoon.product.application.port.out

import com.tistory.kmmoon.product.InventoryEntity


interface CreateInventoryPort {
  fun create(entity: InventoryEntity): InventoryEntity
}