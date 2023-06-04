package com.tistory.kmmoon.product

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "Inventory")
data class InventoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @OneToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var product: ProductEntity,

    var quantity: Int,
    var salesQuantity: Int = 0,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)