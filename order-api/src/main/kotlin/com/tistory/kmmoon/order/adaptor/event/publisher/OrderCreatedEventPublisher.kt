package com.tistory.kmmoon.order.adaptor.event.publisher

import com.tistory.kmmoon.core.event.OrderCreatedEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class OrderCreatedEventPublisher(private val kafkaTemplate: KafkaTemplate<String, OrderCreatedEvent>) {

    fun publish(orderCreatedEvent: OrderCreatedEvent) {
        kafkaTemplate.send("order_created_topic", orderCreatedEvent)
    }
}