package com.tistory.kmmoon.user.domain.response

import java.time.LocalDateTime

data class UserResponse(
  val userId: Long,
  val username: String,
  val password: String,
  val email: String,
  val name: String,
  val phone: String,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime,
)