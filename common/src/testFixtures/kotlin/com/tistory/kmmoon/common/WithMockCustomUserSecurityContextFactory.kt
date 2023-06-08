//package com.tistory.kmmoon.common
//
//import com.tistory.kmmoon.core.security.UserSecurity
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.context.SecurityContext
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.security.test.context.support.WithSecurityContextFactory
//
//class WithMockCustomUserSecurityContextFactory : WithSecurityContextFactory<WithMockCustomUser> {
//    override fun createSecurityContext(annotation: WithMockCustomUser): SecurityContext {
//        val securityContext = SecurityContextHolder.getContext()
//
//        val loginUser = UserSecurity(
//            1,
//            annotation.email,
//            annotation.password,
//            listOf(SimpleGrantedAuthority(annotation.authorities))
//        )
//
//        val authenticationToken = UsernamePasswordAuthenticationToken(loginUser, "", loginUser.authorities)
////        SecurityContextHolder.getContext().authentication = authenticationToken
//
////        val authenticationToken = UsernamePasswordAuthenticationToken(
////            annotation.email,
////            "password",
////            listOf(SimpleGrantedAuthority(annotation.authorities))
////        )
////        SecurityContextHolder.getContext().authentication = authenticationToken
//        securityContext.authentication = authenticationToken
//        return securityContext
//    }
//}