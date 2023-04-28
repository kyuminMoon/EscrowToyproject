package com.tistory.kmmoon.user.application

import com.tistory.kmmoon.user.application.port.`in`.UserCreateUseCase
import com.tistory.kmmoon.user.application.port.`in`.UserDeleteUseCase
import com.tistory.kmmoon.user.application.port.`in`.UserModifyUseCase
import com.tistory.kmmoon.user.application.port.out.CreateUserPort
import com.tistory.kmmoon.user.domain.User
import com.tistory.kmmoon.user.domain.mapper.UserMapper
import com.tistory.kmmoon.user.domain.request.UserCreateRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserCommandService: UserCreateUseCase, UserModifyUseCase, UserDeleteUseCase {

  @Autowired
  lateinit var createUserPort: CreateUserPort

  @Autowired
  lateinit var mapper: UserMapper

  override fun create(request: UserCreateRequest): User {
    val create = createUserPort.create(mapper.createEntity(request))
    return mapper.toData(create)
  }


}