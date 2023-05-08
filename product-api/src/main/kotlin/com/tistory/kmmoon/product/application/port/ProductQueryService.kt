package com.tistory.kmmoon.product.application.port

import com.tistory.kmmoon.product.application.port.`in`.ProductQueryUseCase
import com.tistory.kmmoon.product.application.port.out.QueryProductPort
import com.tistory.kmmoon.product.domain.Product
import com.tistory.kmmoon.product.domain.mapper.ProductMapper
import org.springframework.stereotype.Component

@Component
class ProductQueryService (
  var queryProductPort: QueryProductPort,
  var mapper: ProductMapper
): ProductQueryUseCase {

  override fun findAll(): List<Product>? {
    return mapper.toData(queryProductPort.findAllBy())
  }

  override fun findProductInfo(id: Long): Product? {
    return mapper.toData(queryProductPort.findById(id))
  }

}