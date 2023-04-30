package com.tistory.kmmoon.user.domain.mapper

import com.tistory.kmmoon.user.UserEntity
import com.tistory.kmmoon.user.domain.User
import com.tistory.kmmoon.user.domain.request.UserCreateRequest
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UserMapper {

  @Mapping(target = "id", ignore = true)
  fun createEntity(request: UserCreateRequest): UserEntity

  fun toData(entity: UserEntity?): User

  fun toData(findAllBy: List<UserEntity?>?): List<User>?
}