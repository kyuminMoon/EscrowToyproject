package com.tistory.kmmoon.product

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "products")
data class ProductEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  var id: Long,
  var userId: Long,
  var name: String,
  var description: String,
  var price: BigDecimal,
  @OneToOne
  @JoinTable(
    name = "Inventories", //조인테이블명
    joinColumns = [JoinColumn(name="product_id")],  //외래키
    inverseJoinColumns = [JoinColumn(name="inventory_id")], //반대 엔티티의 외래키
    foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT)
  )
  var inventory: InventoryEntity,
  var active: Boolean = true,
  @CreationTimestamp
  var createdAt: LocalDateTime,
  @UpdateTimestamp
  var updatedAt: LocalDateTime,
)