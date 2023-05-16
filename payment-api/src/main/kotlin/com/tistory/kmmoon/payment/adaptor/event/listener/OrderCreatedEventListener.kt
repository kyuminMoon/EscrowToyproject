package com.tistory.kmmoon.order.domain.event

import com.tistory.kmmoon.order.application.PaymentCreateService
import com.tistory.kmmoon.order.domain.OrderCreatedEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class OrderCreatedEventListener(private val paymentService: PaymentCreateService) {

    @KafkaListener(topics = ["order_created_topic"], groupId = "payment_group")
    fun onOrderCreatedEvent(orderCreatedEvent: OrderCreatedEvent) {
        // 결제 로직 처리
    }
}