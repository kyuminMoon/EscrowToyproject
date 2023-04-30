package com.tistory.kmmoon.order.adaptor.out.persistence

import com.tistory.kmmoon.auth.application.port.out.AuthLoginPort
import com.tistory.kmmoon.auth.application.port.out.AuthSignupPort
import com.tistory.kmmoon.user.UserEntity
import com.tistory.kmmoon.user.UserRepository
import com.tistory.kmmoon.user.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class AuthPersistenceAdapter: AuthLoginPort, AuthSignupPort {

  @Autowired
  lateinit var userRepository: UserRepository;

  override fun login(email: String, password: String): UserEntity {
    return userRepository.findByEmailAndPassword(email, password);
  }

  override fun signup(userEntity: UserEntity): UserEntity? {
    return userRepository.save(userEntity)
  }

  override fun existsEmail(email: String): Boolean {
    return userRepository.findByEmail(email)
  }

}