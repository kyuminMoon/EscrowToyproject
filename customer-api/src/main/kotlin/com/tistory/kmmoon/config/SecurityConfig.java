package com.tistory.kmmoon.config;

import java.util.List;

import com.tistory.kmmoon.core.security.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.tistory.kmmoon.core.config.JwtSecurityConfig;
import com.tistory.kmmoon.core.security.JwtAccessDeniedHandler;
import com.tistory.kmmoon.core.security.JwtAuthenticationEntryPoint;
import com.tistory.kmmoon.core.security.TokenProvider;

// 401 에러로 인해 spring security 3.0 기준으로 찾아서 다시 적용하는걸로...
@Configuration
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // 생성자 통해 스프링 빈 주입받는다.
    @Autowired
    public SecurityConfig(
            TokenProvider tokenProvider,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
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

//            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 우리가 만든 클래스로 인증 실패 핸들링
            .accessDeniedHandler(jwtAccessDeniedHandler) // 커스텀 인가 실패 핸들링

            // enable h2-console // embedded h2를 위한 설정
            .and()
            .headers()
            .frameOptions()
            .sameOrigin()

            .and()
            .formLogin().disable()  //폼로그인 안쓰겠다
            .httpBasic().disable()  //기본 인증방식 사용 X
//            .addFilter(new JwtFilter(authenticationManager())) //필터 추가 , AuthenticationManager 파라미터 필요
//            .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
//            .authorizeRequests()

            // 세션을 사용하지 않기 때문에 STATELESS로 설정
//            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            // api 경로
            .and()
            .authorizeHttpRequests(request -> request
//                .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll() // 회원가입 경로는 인증없이 호출 가능
                .requestMatchers("/auth/signup").permitAll() // 회원가입 경로는 인증없이 호출 가능
                .requestMatchers("/", "/**").permitAll() // 회원가입 경로는 인증없이 호출 가능
//                .requestMatchers("/**/admin/**").hasRole("ADMIN")
//                .requestMatchers("/**/users/**").hasRole("USER")
//                .requestMatchers("*").permitAll()

                .anyRequest().permitAll()
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
            cors.setAllowCredentials(true);
            return cors;
        };
    }
}