package com.tistory.kmmoon.payment.application

import com.tistory.kmmoon.payment.application.port.`in`.PaymentCreateUseCase
import com.tistory.kmmoon.payment.application.port.`in`.PaymentDeleteUseCase
import com.tistory.kmmoon.payment.application.port.`in`.PaymentModifyUseCase
import com.tistory.kmmoon.payment.application.port.out.CreatePaymentPort
import com.tistory.kmmoon.payment.application.port.out.DeletePaymentPort
import com.tistory.kmmoon.payment.application.port.out.ModifyPaymentPort
import org.springframework.stereotype.Service

@Service
class PaymentCommandService (
  val createPaymentPort: CreatePaymentPort,
  val modifyPaymentPort: ModifyPaymentPort,
  val deletePaymentPort: DeletePaymentPort
) : PaymentCreateUseCase, PaymentModifyUseCase, PaymentDeleteUseCase {

}