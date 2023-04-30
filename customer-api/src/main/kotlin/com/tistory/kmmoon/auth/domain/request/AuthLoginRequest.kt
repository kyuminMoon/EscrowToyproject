package com.tistory.kmmoon.auth.domain.request

data class AuthLoginRequest(
  val email: String,
  val password: String,
)