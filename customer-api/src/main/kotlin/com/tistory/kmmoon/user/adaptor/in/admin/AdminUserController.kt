package com.tistory.kmmoon.user.adaptor.`in`.admin

import com.tistory.kmmoon.user.application.port.`in`.UserCreateUseCase
import com.tistory.kmmoon.user.application.port.`in`.UserDeleteUseCase
import com.tistory.kmmoon.user.application.port.`in`.UserModifyUseCase
import com.tistory.kmmoon.user.application.port.`in`.UserQueryUseCase
import com.tistory.kmmoon.user.domain.User
import com.tistory.kmmoon.user.domain.request.UserCreateRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/users")
class AdminUserController {

  @Autowired
  private lateinit var userQueryUseCase: UserQueryUseCase

  @Autowired
  private lateinit var userCreateUseCase: UserCreateUseCase

  @Autowired
  private lateinit var userModifyUseCase: UserModifyUseCase

  @Autowired
  private lateinit var userDeleteUseCase: UserDeleteUseCase

  @GetMapping("/all")
  fun findAll(): ResponseEntity<List<User?>> {
    return ResponseEntity
      .ok()
      .body(userQueryUseCase.findAll())
  }

  @PostMapping
  fun create(@RequestBody request: UserCreateRequest): ResponseEntity<User> {
    return ResponseEntity
      .ok()
      .body(userCreateUseCase.create(request))
  }
}