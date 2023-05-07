package com.tistory.kmmoon.user.application.port.out

import com.tistory.kmmoon.user.UserEntity

interface QueryUserPort {
  fun findAllBy(): List<UserEntity?>?
  fun findById(userId: Long): UserEntity?
}