package com.tistory.kmmoon.auth.domain.request

data class AuthSignupRequest(
  val username: String,
  val password: String,
  val email: String,
  val name: String,
  val phone: String,
)