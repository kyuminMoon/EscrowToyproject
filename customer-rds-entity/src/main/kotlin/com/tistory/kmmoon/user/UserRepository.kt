package com.tistory.kmmoon.user

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
  fun findAllBy(): List<UserEntity>
  fun existsByEmail(email: String): Boolean
  fun findByEmail(email: String): UserEntity?
  @EntityGraph(attributePaths = ["authorities"])
  fun findOneWithAuthoritiesByEmail(email: String): UserEntity?

}