package com.tistory.kmmoon.core.security

import com.tistory.kmmoon.user.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomUserDetailsService(
    val userRepository: UserRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val userEntity = userRepository.findOneWithAuthoritiesByEmail(username) ?: throw UsernameNotFoundException("$username -> 데이터베이스에서 찾을 수 없습니다.")
        return UserSecurity(userEntity)
    }
}