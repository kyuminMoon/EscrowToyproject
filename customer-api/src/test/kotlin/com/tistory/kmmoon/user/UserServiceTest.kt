package com.tistory.kmmoon.user

import com.tistory.kmmoon.common.DatabaseCleanupBefore
import com.tistory.kmmoon.user.application.UserCommandService
import com.tistory.kmmoon.user.application.UserQueryService
import com.tistory.kmmoon.user.domain.request.UserCreateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


internal class UserServiceTest(
  val userCommandService: UserCommandService,
  val userQueryService: UserQueryService
) : DatabaseCleanupBefore() {

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