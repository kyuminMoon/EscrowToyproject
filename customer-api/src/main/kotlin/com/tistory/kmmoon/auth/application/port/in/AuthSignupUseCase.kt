package com.tistory.kmmoon.auth.application.port.`in`

import com.tistory.kmmoon.auth.domain.response.AuthLoginResponse
import com.tistory.kmmoon.user.domain.request.UserCreateRequest

interface AuthSignupUseCase {
  fun signUp(request: UserCreateRequest): AuthLoginResponse?
}