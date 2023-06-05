package com.tistory.kmmoon.core.security

import com.tistory.kmmoon.user.Authority
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserSecurity(
    private val id : Long,
    private val email: String,
    private val password: String,
    private val authorities: Collection<GrantedAuthority?>,
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return authorities
    }

    fun getId(): Long {
        return id
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
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
