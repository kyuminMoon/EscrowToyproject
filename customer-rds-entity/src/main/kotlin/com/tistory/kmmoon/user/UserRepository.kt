package com.tistory.kmmoon.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
  fun findAllBy(): List<UserEntity>
}