package com.tistory.kmmoon.product.application.port.out

import com.tistory.kmmoon.product.application.port.`in`.ProductCreateUseCase
import com.tistory.kmmoon.product.application.port.`in`.ProductDeleteUseCase
import com.tistory.kmmoon.product.application.port.`in`.ProductModifyUseCase
import com.tistory.kmmoon.product.application.port.`in`.ProductQueryUseCase
import com.tistory.kmmoon.product.domain.Product
import com.tistory.kmmoon.product.domain.mapper.ProductMapper
import com.tistory.kmmoon.product.domain.request.ProductCreateRequest
import com.tistory.kmmoon.product.domain.request.ProductModifyRequest
import org.springframework.stereotype.Component

@Component
class CommandProductService (
  val queryProductPort: QueryProductPort,
  val createProductPort: CreateProductPort,
  val modifyProductPort: ModifyProductPort,
  val deleteProductPort: DeleteProductPort,
  val mapper: ProductMapper
): ProductCreateUseCase, ProductModifyUseCase, ProductDeleteUseCase {
  override fun create(userId: Long, productCreateRequest: ProductCreateRequest): Product {
    val entity = mapper.createEntity(productCreateRequest)
    entity.userId = userId
    return mapper.toData(createProductPort.create(entity))
  }

  override fun modify(productModifyRequest: ProductModifyRequest): Product {
    val findById = queryProductPort.findById(productModifyRequest.id) ?: throw Exception()
    if(findById.userId != productModifyRequest.userId)
      throw Exception()
    val entity = mapper.modifyEntity(productModifyRequest)
    return mapper.toData(modifyProductPort.modify(entity))
  }

  override fun delete(productId: Long, userId: Long) {
    deleteProductPort.delete(productId)
  }


}