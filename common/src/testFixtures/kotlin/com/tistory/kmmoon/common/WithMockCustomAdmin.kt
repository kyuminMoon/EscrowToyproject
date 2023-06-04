package com.tistory.kmmoon.common

import org.springframework.security.test.context.support.WithSecurityContext

@Retention(AnnotationRetention.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory::class)
annotation class WithMockCustomAdmin(
    val username: String = "admin1",
    val grade: String = "ADMIN"
)