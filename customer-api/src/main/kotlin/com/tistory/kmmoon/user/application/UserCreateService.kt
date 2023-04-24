package com.tistory.kmmoon.user.application

import com.tistory.kmmoon.user.UserEntity
import com.tistory.kmmoon.user.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserCreateService {

  @Autowired
  lateinit var userRepository: UserRepository;

  fun findAll(): List<UserEntity>? {
    return userRepository.findAllBy();
  }

}