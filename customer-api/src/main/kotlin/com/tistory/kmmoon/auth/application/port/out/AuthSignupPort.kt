package com.tistory.kmmoon.auth.application.port.out

import com.tistory.kmmoon.user.UserEntity

interface AuthSignupPort {
  fun signup(userEntity: UserEntity): UserEntity?

  fun existsEmail(email: String): Boolean
}