package com.tistory.kmmoon.order

import com.tistory.kmmoon.payment.PaymentEntity
import jakarta.persistence.*
import com.tistory.kmmoon.user.UserEntity
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val orderId: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_fk")
    val user: UserEntity,

    val status: String,
    val totalAmount: BigDecimal,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,

    @OneToMany(mappedBy = "order")
    val orderItems: MutableList<OrderItemEntity> = mutableListOf(),

    @OneToOne(mappedBy = "order")
    val payment: PaymentEntity?
)