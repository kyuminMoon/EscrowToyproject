package com.tistory.kmmoon.product.adaptor.out.persistence

import com.tistory.kmmoon.product.ProductEntity
import com.tistory.kmmoon.product.ProductRepository
import com.tistory.kmmoon.product.application.port.out.CreateProductPort
import com.tistory.kmmoon.product.application.port.out.DeleteProductPort
import com.tistory.kmmoon.product.application.port.out.ModifyProductPort
import com.tistory.kmmoon.product.domain.request.ProductCreateRequest
import com.tistory.kmmoon.product.domain.request.ProductModifyRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductCommandPersistenceAdapter(
  var productRepository: ProductRepository
): CreateProductPort, ModifyProductPort, DeleteProductPort {

  override fun create(entity: ProductEntity): ProductEntity {
    return productRepository.save(entity)
  }

  override fun modify(entity: ProductEntity): ProductEntity {
    return productRepository.save(entity)
  }

  override fun delete(productId: Long) {
    productRepository.deleteById(productId)
  }

}