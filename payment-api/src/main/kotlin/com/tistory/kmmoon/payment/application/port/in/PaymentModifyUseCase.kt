package com.tistory.kmmoon.payment.application.port.`in`

import com.tistory.kmmoon.core.security.UserSecurity
import com.tistory.kmmoon.payment.domain.iamport.request.Payment
import com.tistory.kmmoon.payment.domain.iamport.request.PaymentModifyRequest

interface PaymentModifyUseCase {
    fun modify(paymentModifyRequest: PaymentModifyRequest, userSecurity: UserSecurity): Payment?
}
