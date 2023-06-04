package com.tistory.kmmoon.core.security

import com.tistory.kmmoon.user.Authority
import com.tistory.kmmoon.user.UserEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class UserSecurity(
    val user: UserEntity,
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return user.authorities.stream()
            .map { auth: Authority -> SimpleGrantedAuthority(auth.authorityName.name) }
            .toList()
    }

    fun getUserId(): Long {
        return user.id!!
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.email
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
