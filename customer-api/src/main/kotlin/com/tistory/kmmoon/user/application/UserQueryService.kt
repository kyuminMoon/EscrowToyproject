package com.tistory.kmmoon.user.application

import com.tistory.kmmoon.user.application.port.`in`.UserQueryUseCase
import com.tistory.kmmoon.user.application.port.out.QueryUserPort
import com.tistory.kmmoon.user.domain.User
import com.tistory.kmmoon.user.domain.mapper.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserQueryService(
  var queryUserPort: QueryUserPort,
  var userMapper: UserMapper
): UserQueryUseCase {

  override fun findAll(): List<User>? {
    return userMapper.toData(queryUserPort.findAllBy());
  }

  override fun findUserInfo(userId: Long): User? {
    return userMapper.toData(queryUserPort.findById(userId));
  }

}