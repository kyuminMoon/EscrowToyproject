package com.tistory.kmmoon.core.security;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.tistory.kmmoon.user.Authority;
import com.tistory.kmmoon.user.UserEntity;

public class AccountAdapter extends User {
    private UserEntity userEntity;

    public AccountAdapter(UserEntity userEntity) {
        super(userEntity.getEmail(), userEntity.getPassword(), authorities(userEntity.getAuthorities()));
        this.userEntity = userEntity;
    }

    public UserEntity getUserEntity() {
        return this.userEntity;
    }

    private static List<GrantedAuthority> authorities(Set<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName().name()))
                .collect(Collectors.toList());
    }
}