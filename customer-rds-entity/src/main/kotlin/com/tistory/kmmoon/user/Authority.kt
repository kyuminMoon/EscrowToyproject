package com.tistory.kmmoon.user

import jakarta.persistence.*

@Entity
@Table(name = "authority")
data class Authority (
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  val id: Long? = null,

  @Enumerated(EnumType.STRING)
  @Column(name = "authority_name", length = 50)
  val authorityName: UserRole,

  @ManyToMany(mappedBy = "authorities")
  var users: Set<UserEntity> = HashSet()
)