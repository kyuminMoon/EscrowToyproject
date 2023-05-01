package com.tistory.kmmoon.user

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
  fun findAllBy(): List<UserEntity>
  fun findByEmailAndPassword(email: String?, password: String?): UserEntity
  fun existsByEmail(email: String): Boolean

  @EntityGraph(attributePaths = ["authorities"])
  fun findOneWithAuthoritiesByEmail(email: String?): Optional<UserEntity?>? // user를 기준으로 유저를 조회할 때 권한정보도 가져온다.

}