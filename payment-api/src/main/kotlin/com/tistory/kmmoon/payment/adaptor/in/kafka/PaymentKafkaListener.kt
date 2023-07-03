package com.tistory.kmmoon.payment.adaptor.`in`.kafka

// (x)주문 생성({productId, orderId, userId, createdAt})
// (o)> 결제(on-order-create_payment-create : {paymentId, orderId, productId, userId, createdAt})
// (x)> 상품 재고 변경(on-order-create_product-stock-change : {에스크로 관련 정보})
// (x)> 배송(SKIP)
// (x)> 에스크로 주문 확정(on-order-create_escrow-order-confirmation)
// (x)> 주문 확정(on-order-create_order-confirm : {paymentId, productId, orderId, userId, createdAt})
class PaymentKafkaListener {
//    결제(on-order-create_payment-create : {paymentId, orderId, productId, userId, createdAt})

}