package com.tistory.kmmoon.product.domain.mapper

import com.tistory.kmmoon.payment.PaymentEntity
import com.tistory.kmmoon.payment.domain.iamport.request.Payment
import com.tistory.kmmoon.payment.domain.iamport.request.PaymentCreateRequest
import com.tistory.kmmoon.payment.domain.iamport.request.PaymentModifyRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface PaymentMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "active", expression = "java(true)")
  fun createEntity(request: PaymentCreateRequest): PaymentEntity

  fun modifyEntity(request: PaymentModifyRequest): PaymentEntity

  @Mapping(target = "productId", source = "id")
  @Mapping(target = "quantity", source = "inventory.quantity")
  fun toData(entity: PaymentEntity?): Payment

  fun toData(findAllBy: List<PaymentEntity>?): List<Payment>?
}