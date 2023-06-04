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
  val queryProductPort: QueryProductPort,
  val createProductPort: CreateProductPort,
  val modifyProductPort: ModifyProductPort,
  val deleteProductPort: DeleteProductPort,
  val mapper: ProductMapper
): ProductQueryUseCase {

  override fun findAll(): List<Product>? {
    return mapper.toData(queryProductPort.findAllBy())
  }

  override fun findProductInfo(id: Long): Product? {
    return mapper.toData(queryProductPort.findById(id))
  }

  override fun create(userId: Long, productCreateRequest: ProductCreateRequest): Product? {
    val entity = mapper.createEntity(productCreateRequest)
    entity.userId = userId
    return mapper.toData(createProductPort.create(entity))
  }

  override fun modify(productModifyRequest: ProductModifyRequest): Product? {
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