package com.tistory.kmmoon.order.adaptor.out.kafka

import com.tistory.kmmoon.core.event.OrderCreatedEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
// (o)주문 생성({productId, orderId, userId, createdAt})
// (x)> 결제(on-order-create_payment-create : {paymentId, orderId, productId, userId, createdAt})
// (x)> 상품 재고 변경(on-order-create_product-stock-change : {에스크로 관련 정보})
// (x)> 배송(SKIP)
// (o)> 에스크로 주문 확정(on-order-create_escrow-order-confirmation)
// (x)> 주문 확정(on-order-create_order-confirm : {paymentId, productId, orderId, userId, createdAt})
class OrderCreatedEventPublisher(private val kafkaTemplate: KafkaTemplate<String, OrderCreatedEvent>) {

    fun orderCreateEvent(event: OrderCreatedEvent.OrderCreate) {
        kafkaTemplate.send("on-order-create_payment-create", event)
    }
//
//    fun orderConfirmEvent(event: OrderCreatedEvent.OrderConfirm) {
//        kafkaTemplate.send("on-order-create_order-confirm", event)
//    }
}