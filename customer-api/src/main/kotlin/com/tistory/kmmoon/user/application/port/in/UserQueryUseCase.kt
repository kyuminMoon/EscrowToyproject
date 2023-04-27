package com.tistory.kmmoon.user.application.port.`in`

import com.tistory.kmmoon.user.domain.User

interface UserQueryUseCase {
  fun findAll(): List<User?>?
}