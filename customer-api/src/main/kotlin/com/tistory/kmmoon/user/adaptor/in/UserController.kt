package com.tistory.kmmoon.user.adaptor.`in`

import com.tistory.kmmoon.user.UserEntity
import com.tistory.kmmoon.user.application.UserCreateService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

  @Autowired
  private lateinit var userCreateService: UserCreateService

  @GetMapping("/all", produces = ["application/json"])
  fun findAll(): ResponseEntity<List<UserEntity>> {
    return ResponseEntity
      .ok()
      .body(userCreateService.findAll())
  }
}