package com.tistory.kmmoon.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
  fun findAllBy(): List<UserEntity>
  fun findByEmailAndPassword(email: String?, password: String?): UserEntity
  fun findByEmail(email: String): Boolean
}