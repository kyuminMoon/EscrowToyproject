package com.tistory.kmmoon.order.adaptor.out.persistence

import com.tistory.kmmoon.user.UserEntity
import com.tistory.kmmoon.user.UserRepository
import com.tistory.kmmoon.user.application.port.out.QueryUserPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserQueryPersistenceAdapter(
  var userRepository: UserRepository
): QueryUserPort {

  override fun findAllBy(): List<UserEntity> {
    return userRepository.findAllBy()
  }

  override fun findById(userId: Long): UserEntity? {
    return userRepository.findById(userId).orElse(null)
  }

}