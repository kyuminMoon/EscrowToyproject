package com.tistory.kmmoon.user

import jakarta.persistence.*

@Entity
@Table(name = "authority")
data class Authority (
  @Id
  @Enumerated(EnumType.STRING)
  @Column(name = "authority_name", length = 50)
  var authorityName: UserRole,
)