package com.tistory.kmmoon.product.adaptor.out.persistence

import com.tistory.kmmoon.product.InventoryEntity
import com.tistory.kmmoon.product.InventoryRepository
import com.tistory.kmmoon.product.ProductEntity
import com.tistory.kmmoon.product.ProductRepository
import com.tistory.kmmoon.product.application.port.out.*
import org.springframework.stereotype.Component

@Component
class InventoryCommandPersistenceAdapter(
  var inventoryRepository: InventoryRepository
): CreateInventoryPort, ModifyInventoryPort, DeleteInventoryPort {

  override fun create(entity: InventoryEntity): InventoryEntity {
    return inventoryRepository.save(entity)
  }

  override fun modify(entity: InventoryEntity): InventoryEntity {
    return inventoryRepository.save(entity)
  }

  override fun delete(inventoryId: Long) {
    inventoryRepository.deleteById(inventoryId)
  }

}