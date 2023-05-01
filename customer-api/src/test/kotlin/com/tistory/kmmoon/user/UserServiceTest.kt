package com.tistory.kmmoon.user

import com.tistory.kmmoon.config.DatabaseCleanupBefore
import com.tistory.kmmoon.user.application.UserCommandService
import com.tistory.kmmoon.user.application.UserQueryService
import com.tistory.kmmoon.user.domain.request.UserCreateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


internal class UserServiceTest : DatabaseCleanupBefore() {

  @Autowired
  lateinit var userCommandService: UserCommandService

  @Autowired
  lateinit var userQueryService: UserQueryService


  var request = UserCreateRequest(
    password = "password",
    email = "email@email.com",
    name = "name",
    phone = "010-0000-0000"
  )

  @Test
  fun 유저_생성_및_조회() {
    val userData = userCommandService.create(request)

    val userList = userQueryService.findAll() ?: emptyList()

    assertAll(
      { assertThat(userList.first().id).isEqualTo(userData.id) }, //
    )
  }
}