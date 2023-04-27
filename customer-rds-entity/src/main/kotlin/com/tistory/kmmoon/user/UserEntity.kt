package com.tistory.kmmoon.user

import jakarta.persistence.*
import org.hibernate.Hibernate
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class UserEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  val userId: Long,
  var username: String,
  var password: String,
  var email: String,
  var name: String,
  var phone: String,
  @CreationTimestamp
  val createdAt: LocalDateTime,
  @UpdateTimestamp
  var updatedAt: LocalDateTime,
)