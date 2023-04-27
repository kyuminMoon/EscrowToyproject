package com.tistory.kmmoon.user.application

import com.tistory.kmmoon.user.application.port.`in`.UserQueryUseCase
import com.tistory.kmmoon.user.application.port.out.QueryUserPort
import com.tistory.kmmoon.user.domain.User
import com.tistory.kmmoon.user.domain.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserQueryService: UserQueryUseCase {

  @Autowired
  lateinit var queryUserPort: QueryUserPort

  lateinit var userMapper: UserMapper

  override fun findAll(): List<User>? {
    return userMapper.toData(queryUserPort.findAllBy());
  }

}