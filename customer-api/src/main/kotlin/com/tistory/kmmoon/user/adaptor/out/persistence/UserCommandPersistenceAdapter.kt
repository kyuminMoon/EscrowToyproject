package com.tistory.kmmoon.order.adaptor.out.persistence

import com.tistory.kmmoon.user.UserEntity
import com.tistory.kmmoon.user.UserRepository
import com.tistory.kmmoon.user.application.port.out.CreateUserPort
import com.tistory.kmmoon.user.application.port.out.DeleteUserPort
import com.tistory.kmmoon.user.application.port.out.ModifyUserPort
import org.springframework.stereotype.Component

@Component
class UserCommandPersistenceAdapter(
  var userRepository: UserRepository
): CreateUserPort, ModifyUserPort, DeleteUserPort {
  override fun create(userEntity: UserEntity): UserEntity {
    return userRepository.save(userEntity)
  }

  override fun existsEmail(email: String): Boolean {
    return userRepository.existsByEmail(email)
  }

}