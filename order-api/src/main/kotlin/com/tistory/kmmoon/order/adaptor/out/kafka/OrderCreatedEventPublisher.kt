package com.tistory.kmmoon.order.adaptor.out.kafka

import com.tistory.kmmoon.core.event.OrderCreatedEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class OrderCreatedEventPublisher(private val kafkaTemplate: KafkaTemplate<String, OrderCreatedEvent>) {

    // 주문 -> 결제 -> 상품 재고 변경
    //
    fun publish(orderCreatedEvent: OrderCreatedEvent.OrderCreate) {
        kafkaTemplate.send("on-order-create_order-create", orderCreatedEvent)
    }

    fun publish(orderCreatedEvent: OrderCreatedEvent.OrderConfirm) {
        kafkaTemplate.send("on-order-create_order-confirm", orderCreatedEvent)
    }
}