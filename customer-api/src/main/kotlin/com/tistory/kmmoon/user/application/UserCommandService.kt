package com.tistory.kmmoon.user.application

import com.tistory.kmmoon.core.exception.ErrorMessage
import com.tistory.kmmoon.core.exception.UserGuideException
import com.tistory.kmmoon.user.application.port.`in`.UserCreateUseCase
import com.tistory.kmmoon.user.application.port.`in`.UserDeleteUseCase
import com.tistory.kmmoon.user.application.port.`in`.UserModifyUseCase
import com.tistory.kmmoon.user.application.port.out.CreateUserPort
import com.tistory.kmmoon.user.domain.User
import com.tistory.kmmoon.user.domain.mapper.UserMapper
import com.tistory.kmmoon.user.domain.request.UserCreateRequest
import org.springframework.stereotype.Component

@Component
class UserCommandService(
  var createUserPort: CreateUserPort,
  var mapper: UserMapper
): UserCreateUseCase, UserModifyUseCase, UserDeleteUseCase {

  override fun create(request: UserCreateRequest): User {
    // 유저 검증 로직 추가
    val existsEmail = createUserPort.existsEmail(request.email)

    if(existsEmail)
      throw UserGuideException(ErrorMessage.ALREADY_IN_USE)

    val create = createUserPort.create(mapper.createEntity(request))
    return mapper.toData(create)
  }


}