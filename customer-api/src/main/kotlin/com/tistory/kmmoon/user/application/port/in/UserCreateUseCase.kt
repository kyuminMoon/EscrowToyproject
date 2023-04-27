package com.tistory.kmmoon.user.application.port.`in`

import com.tistory.kmmoon.user.domain.User
import com.tistory.kmmoon.user.domain.request.UserCreateRequest

interface UserCreateUseCase {
  fun create(request: UserCreateRequest): User
}