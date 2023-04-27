package com.tistory.kmmoon.order.adaptor.out.persistence

import com.tistory.kmmoon.user.UserEntity
import com.tistory.kmmoon.user.UserRepository
import com.tistory.kmmoon.user.application.port.out.CreateUserPort
import com.tistory.kmmoon.user.application.port.out.DeleteUserPort
import com.tistory.kmmoon.user.application.port.out.ModifyUserPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserCommandPersistenceAdapter: CreateUserPort, ModifyUserPort, DeleteUserPort {
  @Autowired
  lateinit var userRepository: UserRepository
  override fun create(userEntity: UserEntity): UserEntity {
    return userRepository.save(userEntity);
  }

}