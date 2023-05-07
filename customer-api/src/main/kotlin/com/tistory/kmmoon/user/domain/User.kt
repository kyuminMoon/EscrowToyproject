package com.tistory.kmmoon.user.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime

data class User(
  val id: Long,
  val email: String?,
  @JsonIgnore
  val password: String?,
  val name: String?,
  val phone: String?,
  val createdAt: LocalDateTime,
  val updatedAt: LocalDateTime?,
)