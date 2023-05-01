package com.tistory.kmmoon.user

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class UserEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  val id: Long? = null,
  @Column(unique = true)
  var email: String,
  var password: String,
  var name: String,
  var phone: String,

  @ManyToMany
  @JoinTable(
    name = "account_authority",
    joinColumns = [JoinColumn(name = "user_id")],
    inverseJoinColumns = [JoinColumn(name = "authority_name")]
  )
  var authorities: Set<Authority>,

  @CreationTimestamp
  val createdAt: LocalDateTime? = null,
  @UpdateTimestamp
  var updatedAt: LocalDateTime? = null,
)