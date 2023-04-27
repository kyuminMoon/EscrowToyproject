package com.tistory.kmmoon.user.domain

import java.time.LocalDateTime
data class User(
  val userId: Long,
  val username: String?,
  val password: String?,
  val email: String?,
  val name: String?,
  val phone: String?,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime?,
)