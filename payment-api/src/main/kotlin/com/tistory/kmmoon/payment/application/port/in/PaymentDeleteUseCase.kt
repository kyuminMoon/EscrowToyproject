package com.tistory.kmmoon.payment.application.port.`in`

import com.tistory.kmmoon.core.security.UserSecurity
import com.tistory.kmmoon.payment.domain.iamport.request.Payment

interface PaymentDeleteUseCase {
    fun delete(paymentId: Long, userSecurity: UserSecurity)
}
