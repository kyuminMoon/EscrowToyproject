package com.tistory.kmmoon.user.service

import com.tistory.kmmoon.config.DatabaseCleanupBefore
import com.tistory.kmmoon.user.application.UserCommandService
import com.tistory.kmmoon.user.application.UserQueryService
import com.tistory.kmmoon.user.domain.request.UserCreateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
internal class UserServiceTest : DatabaseCleanupBefore() {

  @Autowired
  lateinit var userCommandService: UserCommandService

  @Autowired
  lateinit var userQueryService: UserQueryService


  lateinit var request: UserCreateRequest

  @BeforeEach
  fun setUp() {
    request = UserCreateRequest(
      password = "password",
      username = "username",
      email = "email@email.com",
      name = "name",
      phone = "010-0000-0000"
    )
  }

  @Test
  fun 유저_생성() {
    val userData = userCommandService.create(request)

    val userList = userQueryService.findAll() ?: emptyList()

    assertAll(
      { assertThat(userList.first().userId).isEqualTo(userData.userId) }, //
    )
  }
}