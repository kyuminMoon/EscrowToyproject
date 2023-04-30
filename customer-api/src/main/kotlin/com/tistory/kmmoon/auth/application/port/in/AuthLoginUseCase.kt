package com.tistory.kmmoon.auth.application.port.`in`

import com.tistory.kmmoon.auth.domain.response.AuthLoginResponse

interface AuthLoginUseCase {
  fun login(email: String, password: String): AuthLoginResponse
}