package com.tistory.kmmoon.payment.application

import com.tistory.kmmoon.core.security.UserSecurity
import com.tistory.kmmoon.payment.application.port.`in`.PaymentCreateUseCase
import com.tistory.kmmoon.payment.application.port.`in`.PaymentDeleteUseCase
import com.tistory.kmmoon.payment.application.port.`in`.PaymentModifyUseCase
import com.tistory.kmmoon.payment.application.port.out.CreatePaymentPort
import com.tistory.kmmoon.payment.application.port.out.DeletePaymentPort
import com.tistory.kmmoon.payment.application.port.out.ModifyPaymentPort
import com.tistory.kmmoon.payment.application.port.out.QueryPaymentPort
import com.tistory.kmmoon.payment.domain.iamport.request.Payment
import com.tistory.kmmoon.payment.domain.iamport.request.PaymentCreateRequest
import com.tistory.kmmoon.payment.domain.iamport.request.PaymentModifyRequest
import com.tistory.kmmoon.payment.domain.payment.mapper.PaymentMapper
import org.springframework.stereotype.Service

@Service
class PaymentCommandService (
  val queryPaymentPort: QueryPaymentPort,
  val createPaymentPort: CreatePaymentPort,
  val modifyPaymentPort: ModifyPaymentPort,
  val deletePaymentPort: DeletePaymentPort,
  val mapper: PaymentMapper
) : PaymentCreateUseCase, PaymentModifyUseCase, PaymentDeleteUseCase {
  override fun create(paymentCreateRequest: PaymentCreateRequest, userSecurity: UserSecurity): Payment? {
    // TODO 상품의 재고를 수정해줘야함
    // TODO 아임포트 결제모듈 연동 필요
    val entity = mapper.createEntity(paymentCreateRequest)
    return mapper.toData(createPaymentPort.create(entity))
  }

  override fun delete(paymentId: Long, userSecurity: UserSecurity) {
    // TODO 상품의 재고를 수정해줘야함
    // TODO 아임포트 결제모듈 연동 필요
    val entity = queryPaymentPort.findById(paymentId)
    deletePaymentPort.delete(entity)
  }

  override fun modify(paymentModifyRequest: PaymentModifyRequest, userSecurity: UserSecurity): Payment? {
    // TODO 상품의 재고를 수정해줘야함
    // TODO 아임포트 결제모듈 연동 필요
    val entity = queryPaymentPort.findById(paymentModifyRequest.id)
    return mapper.toData(modifyPaymentPort.modify(entity))
  }
}
