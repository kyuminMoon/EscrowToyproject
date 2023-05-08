package com.tistory.kmmoon.product.domain.mapper

import com.tistory.kmmoon.product.ProductEntity
import com.tistory.kmmoon.product.domain.Product
import com.tistory.kmmoon.product.domain.request.ProductCreateRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ProductMapper {

  @Mapping(target = "id", ignore = true)
  fun createEntity(request: ProductCreateRequest): ProductEntity

  fun toData(entity: ProductEntity?): Product

  fun toData(findAllBy: List<ProductEntity>?): List<Product>?
}