package com.tistory.kmmoon.common

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithSecurityContextFactory

class WithMockCustomUserSecurityContextFactory : WithSecurityContextFactory<WithMockCustomUser> {
    override fun createSecurityContext(annotation: WithMockCustomUser): SecurityContext {
        val securityContext = SecurityContextHolder.createEmptyContext()
        val authenticationToken = UsernamePasswordAuthenticationToken(
            annotation.username,
            "password",
            listOf(SimpleGrantedAuthority(annotation.grade))
        )
        securityContext.authentication = authenticationToken
        return securityContext
    }
}