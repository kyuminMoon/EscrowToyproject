package com.tistory.kmmoon.common

import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DatabaseCleanupBefore {
  @Autowired
  private val databaseCleanup: DatabaseCleanup? = null

  @LocalServerPort
  private val port = 0

  @BeforeEach
  fun setUp() {
    if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
      RestAssured.port = port
      databaseCleanup!!.afterPropertiesSet()
    }
    databaseCleanup!!.execute()
  }
}