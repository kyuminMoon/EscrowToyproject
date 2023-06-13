package com.tistory.kmmoon.payment.adaptor.out.persistence

import com.tistory.kmmoon.payment.PaymentRepository
import com.tistory.kmmoon.payment.application.port.out.CreatePaymentPort
import com.tistory.kmmoon.payment.application.port.out.DeletePaymentPort
import com.tistory.kmmoon.payment.application.port.out.ModifyPaymentPort
import org.springframework.stereotype.Component

@Component
class PaymentCommandPersistenceAdapter(
    val paymentRepository: PaymentRepository
) : CreatePaymentPort, ModifyPaymentPort, DeletePaymentPort {

}