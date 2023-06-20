package com.tistory.kmmoon.payment.application

import com.tistory.kmmoon.core.security.UserSecurity
import com.tistory.kmmoon.payment.application.port.`in`.PaymentCreateUseCase
import com.tistory.kmmoon.payment.application.port.`in`.PaymentDeleteUseCase
import com.tistory.kmmoon.payment.application.port.`in`.PaymentModifyUseCase
import com.tistory.kmmoon.payment.application.port.out.CreatePaymentPort
import com.tistory.kmmoon.payment.application.port.out.DeletePaymentPort
import com.tistory.kmmoon.payment.application.port.out.ModifyPaymentPort
import com.tistory.kmmoon.payment.domain.iamport.request.Payment
import com.tistory.kmmoon.payment.domain.iamport.request.PaymentCreateRequest
import com.tistory.kmmoon.payment.domain.iamport.request.PaymentModifyRequest
import com.tistory.kmmoon.payment.domain.payment.mapper.PaymentMapper
import org.springframework.stereotype.Service

@Service
class PaymentCommandService (
  val createPaymentPort: CreatePaymentPort,
  val modifyPaymentPort: ModifyPaymentPort,
  val deletePaymentPort: DeletePaymentPort,
  val mapper: PaymentMapper
) : PaymentCreateUseCase, PaymentModifyUseCase, PaymentDeleteUseCase {
  override fun create(paymentCreateRequest: PaymentCreateRequest, userSecurity: UserSecurity): Payment? {
    val entity = mapper.createEntity(paymentCreateRequest)

    createPaymentPort.create(entity)
    return null
  }

  override fun delete(paymentId: Long, userSecurity: UserSecurity): Payment? {
    TODO("Not yet implemented")
  }

  override fun modify(paymentModifyRequest: PaymentModifyRequest, userSecurity: UserSecurity): Payment? {
    TODO("Not yet implemented")
  }
}
