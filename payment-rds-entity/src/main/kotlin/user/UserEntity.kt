package user

import payment.PaymentEntity
import jakarta.persistence.*
import order.OrderEntity
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class UserEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  val userId: Long,

  val username: String,
  val password: String,
  val email: String,
  val name: String,
  val phone: String,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,

  @OneToMany(mappedBy = "user")
  val orders: MutableList<OrderEntity> = mutableListOf(),

  @OneToMany(mappedBy = "user")
  val payments: MutableList<PaymentEntity> = mutableListOf()
)