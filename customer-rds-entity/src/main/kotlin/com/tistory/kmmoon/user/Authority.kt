package com.tistory.kmmoon.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table

@Entity
@Table(name = "authority")
data class Authority (
  @Column(name = "authority_name", length = 50)
  @Id
  private val authorityName: UserRole,

  @ManyToMany(mappedBy = "authorities")
  var users: Set<UserEntity> = HashSet()
)