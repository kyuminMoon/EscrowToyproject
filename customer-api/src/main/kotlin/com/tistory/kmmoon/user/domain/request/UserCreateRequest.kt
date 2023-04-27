package com.tistory.kmmoon.user.domain.request

data class UserCreateRequest(
  val username: String,
  val password: String,
  val email: String,
  val name: String,
  val phone: String,
)