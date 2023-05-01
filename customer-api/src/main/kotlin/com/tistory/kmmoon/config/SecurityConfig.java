package com.tistory.kmmoon.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.tistory.kmmoon.core.config.JwtSecurityConfig;
import com.tistory.kmmoon.core.security.JwtAccessDeniedHandler;
import com.tistory.kmmoon.core.security.JwtAuthenticationEntryPoint;
import com.tistory.kmmoon.core.security.TokenProvider;

@Configuration
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // 생성자 통해 스프링 빈 주입받는다.
    public SecurityConfig(
            TokenProvider tokenProvider,
            CorsFilter corsFilter,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.corsFilter = corsFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
            .requestMatchers(
                "/assets/**",
                "/h2-console/**",
                "/api/hello2",
                "/h2/**",
                "/favicon.ico",
                "/error"
            );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors()
            .configurationSource(getCorsConfigurationSource());

        httpSecurity
            // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
            .csrf().disable()

            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 우리가 만든 클래스로 인증 실패 핸들링
            .accessDeniedHandler(jwtAccessDeniedHandler) // 커스텀 인가 실패 핸들링

            // enable h2-console // embedded h2를 위한 설정
            .and()
            .headers()
            .frameOptions()
            .sameOrigin()

            // 세션을 사용하지 않기 때문에 STATELESS로 설정
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            // api 경로
            .and()
            .authorizeHttpRequests(request -> request
                .requestMatchers(HttpMethod.POST, "/**/auth/**").permitAll() // 회원가입 경로는 인증없이 호출 가능
                .requestMatchers("/**/admin/**").hasRole("ADMIN")
                .requestMatchers("/**/users/**").hasRole("USER")

                .anyRequest().authenticated() // 나머지 경로는 jwt 인증 해야함
            )
            .apply(new JwtSecurityConfig(tokenProvider)); // JwtSecurityConfig 적용
        return httpSecurity.build();
    }


    private CorsConfigurationSource getCorsConfigurationSource() {
        return request -> {
            var cors = new CorsConfiguration();

            cors.setAllowedOrigins(List.of("*"));
            cors.setAllowedHeaders(List.of("*"));
            cors.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

            return cors;
        };
    }
}