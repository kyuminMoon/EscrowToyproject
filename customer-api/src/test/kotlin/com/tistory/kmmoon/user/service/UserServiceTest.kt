package com.tistory.kmmoon.user.service

import com.tistory.kmmoon.config.DatabaseCleanupBefore
import com.tistory.kmmoon.order.adaptor.out.persistence.UserCommandPersistenceAdapter
import com.tistory.kmmoon.order.adaptor.out.persistence.UserQueryPersistenceAdapter
import com.tistory.kmmoon.user.UserRepository
import com.tistory.kmmoon.user.application.UserCommandService
import com.tistory.kmmoon.user.application.UserQueryService
import com.tistory.kmmoon.user.application.port.out.CreateUserPort
import com.tistory.kmmoon.user.application.port.out.QueryUserPort
import com.tistory.kmmoon.user.domain.request.UserCreateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@EnableAutoConfiguration
@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [
  UserCommandService::class,
  UserQueryService::class,
  UserCommandPersistenceAdapter::class,
  UserQueryPersistenceAdapter::class,
])
internal class UserServiceTest : DatabaseCleanupBefore() {

  @Autowired
  lateinit var userCommandService: UserCommandService

  @Autowired
  lateinit var userQueryService: UserQueryService

  lateinit var request: UserCreateRequest

  @BeforeEach
  override fun setUp() {
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