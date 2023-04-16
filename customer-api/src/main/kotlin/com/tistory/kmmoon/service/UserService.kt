package com.tistory.kmmoon.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import com.tistory.kmmoon.user.UserEntity
import com.tistory.kmmoon.user.UserRepository

@Component
class UserService {

  @Autowired
  lateinit var userRepository: UserRepository;

  fun findAll(): List<UserEntity>? {
    return userRepository.findAllBy();
  }

}