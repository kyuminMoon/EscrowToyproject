package com.tistory.kmmoon.user.adaptor.`in`.user

import com.tistory.kmmoon.user.application.port.`in`.UserModifyUseCase
import com.tistory.kmmoon.user.application.port.`in`.UserQueryUseCase
import com.tistory.kmmoon.user.domain.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user/customer")
class UserController (
  var userQueryUseCase: UserQueryUseCase,
  var userModifyUseCase: UserModifyUseCase
) {
  @GetMapping("/{userId}")
  fun findUserInfo(@PathVariable userId: Long): ResponseEntity<User?> {
    return ResponseEntity
      .ok()
      .body(userQueryUseCase.findUserInfo(userId))
  }
}