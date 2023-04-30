package com.tistory.kmmoon.auth.domain.response

data class AuthLoginResponse (
  val accessToken: String,
  val refreshToken: String
)