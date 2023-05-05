package com.tistory.kmmoon.core.security

import com.tistory.kmmoon.user.Authority
import com.tistory.kmmoon.user.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserSecurity(private val users: UserEntity) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return users.authorities.stream()
            .map { auth: Authority -> SimpleGrantedAuthority(auth.toString()) }
            .toList()
    }

    override fun getPassword(): String {
        return users.password
    }

    override fun getUsername(): String {
        return users.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
