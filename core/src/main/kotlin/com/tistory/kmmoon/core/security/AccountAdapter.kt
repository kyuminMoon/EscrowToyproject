//package com.tistory.kmmoon.core.security
//
//import com.tistory.kmmoon.user.Authority
//import com.tistory.kmmoon.user.UserEntity
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.userdetails.User
//import java.util.stream.Collectors
//
//class AccountAdapter(val userEntity: UserEntity) : User(
//    userEntity.email, userEntity.password, authorities(userEntity.roles)
//) {
//
//    companion object {
//        private fun authorities(authorities: Set<Authority>): List<GrantedAuthority> {
//            return authorities.stream()
//                .map { (_, authorityName): Authority ->
//                    SimpleGrantedAuthority(
//                        authorityName
//                    )
//                }
//                .collect(Collectors.toList())
//        }
//    }
//}