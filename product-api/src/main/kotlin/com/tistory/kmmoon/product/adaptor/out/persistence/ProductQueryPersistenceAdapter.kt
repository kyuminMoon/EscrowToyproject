package com.tistory.kmmoon.product.adaptor.out.persistence

import com.tistory.kmmoon.product.ProductEntity
import com.tistory.kmmoon.product.ProductRepository
import com.tistory.kmmoon.product.application.port.out.QueryProductPort
import org.springframework.stereotype.Component

@Component
class ProductQueryPersistenceAdapter(
  var productRepository: ProductRepository
): QueryProductPort {

  override fun findAllBy(): List<ProductEntity> {
    return productRepository.findAllBy()
  }

  override fun findById(id: Long): ProductEntity? {
    return productRepository.findById(id).orElse(null)
  }

}