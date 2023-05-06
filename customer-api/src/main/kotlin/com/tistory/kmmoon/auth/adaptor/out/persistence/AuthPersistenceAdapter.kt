package com.tistory.kmmoon.order.adaptor.out.persistence

import com.tistory.kmmoon.auth.application.port.out.AuthLoginPort
import com.tistory.kmmoon.auth.application.port.out.AuthSignupPort
import com.tistory.kmmoon.user.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class AuthPersistenceAdapter: AuthLoginPort, AuthSignupPort {

  @Autowired
  lateinit var userRepository: UserRepository

  @Autowired
  lateinit var authorityRepository: AuthorityRepository

  override fun findByEmail(email: String): UserEntity? {
    return userRepository.findByEmail(email);
  }

  override fun signup(userEntity: UserEntity): UserEntity? {
    val authorities = authorityRepository.saveAll(userEntity.authorities)
    userEntity.password = userEntity.password
    userEntity.authorities = authorities.toSet()
    return userRepository.save(userEntity)
  }

  override fun existsEmail(email: String): Boolean {
    return userRepository.existsByEmail(email)
  }

}