package com.tistory.kmmoon.product.application.port

import com.tistory.kmmoon.product.application.port.`in`.ProductQueryUseCase
import com.tistory.kmmoon.product.application.port.out.CreateProductPort
import com.tistory.kmmoon.product.application.port.out.DeleteProductPort
import com.tistory.kmmoon.product.application.port.out.ModifyProductPort
import com.tistory.kmmoon.product.application.port.out.QueryProductPort
import com.tistory.kmmoon.product.domain.Product
import com.tistory.kmmoon.product.domain.mapper.ProductMapper
import com.tistory.kmmoon.product.domain.request.ProductCreateRequest
import com.tistory.kmmoon.product.domain.request.ProductModifyRequest
import org.springframework.stereotype.Component

@Component
class ProductQueryService (
  var queryProductPort: QueryProductPort,
  var createProductPort: CreateProductPort,
  var modifyProductPort: ModifyProductPort,
  var deleteProductPort: DeleteProductPort,
  var mapper: ProductMapper
): ProductQueryUseCase {

  override fun findAll(): List<Product>? {
    return mapper.toData(queryProductPort.findAllBy())
  }

  override fun findProductInfo(id: Long): Product? {
    return mapper.toData(queryProductPort.findById(id))
  }

  override fun create(productCreateRequest: ProductCreateRequest): Product? {
    val entity = mapper.createEntity(productCreateRequest)
    return mapper.toData(createProductPort.create(entity))
  }

  override fun modify(productModifyRequest: ProductModifyRequest): Product? {
    val entity = mapper.modifyEntity(productModifyRequest)
    return mapper.toData(modifyProductPort.modify(entity))
  }

  override fun delete(productId: Long) {
    deleteProductPort.delete(productId)
  }


}