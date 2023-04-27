//package com.tistory.kmmoon.order.domain.event
//
//import com.example.order.domain.event.OrderCreatedEvent
//import com.example.payment.application.service.PaymentService
//import org.springframework.kafka.annotation.KafkaListener
//import org.springframework.stereotype.Component
//
//@Component
//class OrderCreatedEventListener(private val paymentService: PaymentService) {
//
//    @KafkaListener(topics = ["order_created_topic"], groupId = "payment_group")
//    fun onOrderCreatedEvent(orderCreatedEvent: OrderCreatedEvent) {
//        // 결제 로직 처리
//    }
//}