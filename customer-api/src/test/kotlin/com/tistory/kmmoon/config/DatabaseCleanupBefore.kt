package com.tistory.kmmoon.config

import com.tistory.kmmoon.common.DatabaseCleanup
import com.tistory.kmmoon.user.UserRepository
import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.web.server.LocalServerPort

@DataJpaTest
class DatabaseCleanupBefore {
  @Autowired
  private val databaseCleanUp: DatabaseCleanup? = null

  @LocalServerPort
  private val port = 0

  @Autowired
  lateinit var userRepository: UserRepository
  @BeforeEach
  fun setUp() {
    if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
      RestAssured.port = port
      databaseCleanUp!!.afterPropertiesSet()
    }
    databaseCleanUp!!.execute()
  }
}