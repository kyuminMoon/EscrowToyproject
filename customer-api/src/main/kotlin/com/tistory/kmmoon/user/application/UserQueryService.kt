package com.tistory.kmmoon.user.application

import com.tistory.kmmoon.user.UserEntity
import com.tistory.kmmoon.user.application.port.`in`.UserQueryUseCase
import com.tistory.kmmoon.user.application.port.out.QueryUserPort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserQueryService: UserQueryUseCase {

  @Autowired
  lateinit var queryUserPort: QueryUserPort;

  override fun findAll(): List<UserEntity>? {
    return queryUserPort.findAllBy();
  }

}