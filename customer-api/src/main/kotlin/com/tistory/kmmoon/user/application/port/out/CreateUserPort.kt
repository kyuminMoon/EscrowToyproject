package com.tistory.kmmoon.user.application.port.out

import com.tistory.kmmoon.user.UserEntity

interface CreateUserPort {
  fun create(userEntity: UserEntity): UserEntity
}