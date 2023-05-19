package com.tistory.kmmoon.payment.adaptor.event.listener

import com.tistory.kmmoon.core.event.OrderCreatedEvent
import com.tistory.kmmoon.payment.application.PaymentCreateService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class OrderCreatedEventListener(
    val paymentService: PaymentCreateService
) {

    @KafkaListener(topics = ["order_created_topic"], groupId = "payment_group")
    fun onOrderCreatedEvent(orderCreatedEvent: OrderCreatedEvent) {
        // 결제 로직 처리
    }
}