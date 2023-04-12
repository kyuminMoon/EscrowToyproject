import jakarta.persistence.*
import order.OrderEntity
import user.UserEntity
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "payments")
data class PaymentEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  val paymentId: Long,

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  val order: OrderEntity,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  val user: UserEntity,

  val amount: BigDecimal,
  val paymentMethod: String,
  val paymentStatus: String,
  val escrowStatus: String,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime
)