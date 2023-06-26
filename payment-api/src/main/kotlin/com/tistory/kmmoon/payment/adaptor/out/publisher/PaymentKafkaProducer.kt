package com.tistory.kmmoon.payment.adaptor.out.publisher

import com.tistory.kmmoon.core.event.ProductInventoryDeductionEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.ExecutionException


@Service
class PaymentKafkaProducer(kafkaTemplate: KafkaTemplate<String, ProductInventoryDeductionEvent>) {
    private val kafkaTemplate: KafkaTemplate<String, ProductInventoryDeductionEvent>

    init {
        this.kafkaTemplate = kafkaTemplate
    }

    @Throws(ExecutionException::class, InterruptedException::class)
    fun send(request: ProductInventoryDeductionEvent) {
        val event: ProductInventoryDeductionEvent =
            ProductInventoryDeductionEvent.create(request.getId(), request.getServiceDeliveryType(), request.getServiceDeliveryId())
        kafkaTemplate.send(TOPIC, event.getId(), event).get()
    }

    companion object {
        private const val TOPIC = "product-inventory-deduction"
    }
}