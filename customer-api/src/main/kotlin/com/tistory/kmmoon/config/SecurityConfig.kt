package com.tistory.kmmoon.config

import com.tistory.kmmoon.core.config.JwtAuthFilter
import com.tistory.kmmoon.core.security.CustomUserDetailsService
import jakarta.servlet.FilterConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

// 401 에러로 인해 spring security 3.0 기준으로 찾아서 다시 적용하는걸로...
@Configuration
@EnableWebSecurity
@EnableMethodSecurity //@RequiredArgsConstructor
class SecurityConfig // 생성자 통해 스프링 빈 주입받는다.
@Autowired constructor(
    private val jwtAuthFilter: JwtAuthFilter,
//    private val jpaUserDetailsService: CustomUserDetailsService
) {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder(12)

    @Bean
    @Throws(Exception::class)
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
//        httpSecurity.cors()
//            .configurationSource(getCorsConfigurationSource());
        httpSecurity // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
            //                .cors().disable()
            .csrf().disable() //            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
            //            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
            //            .exceptionHandling()
            //            .authenticationEntryPoint(jwtAuthenticationEntryPoint) // 우리가 만든 클래스로 인증 실패 핸들링
            //            .accessDeniedHandler(jwtAccessDeniedHandler) // 커스텀 인가 실패 핸들링
            // enable h2-console // embedded h2를 위한 설정
            //            .and()
            //            .headers()
            //            .frameOptions()
            //            .sameOrigin()
            //            .and()
            //            .formLogin().disable()  //폼로그인 안쓰겠다
            //            .httpBasic().disable()  //기본 인증방식 사용 X
            //            .addFilter(new JwtFilter(authenticationManager())) //필터 추가 , AuthenticationManager 파라미터 필요
            //            .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
            //            .authorizeRequests()
            // 세션을 사용하지 않기 때문에 STATELESS로 설정
            //            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // api 경로
            .and() //                .securityMatcher("/api/**")
            .authorizeHttpRequests { authorize ->
                authorize //                .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll() // 회원가입 경로는 인증없이 호출 가능
                    //                .requestMatchers("/auth/signup").permitAll() // 회원가입 경로는 인증없이 호출 가능
                    //                .requestMatchers("/", "/**").permitAll() // 회원가입 경로는 인증없이 호출 가능
                    //                .requestMatchers("/**/admin/**").hasRole("ADMIN")
                    //                .requestMatchers("/**/users/**").hasRole("USER")
                    //                .requestMatchers("*").permitAll()
                    //                .anyRequest().permitAll()
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/api/auth/signup").permitAll() // 이 줄을 추가하여 /api/auth/signup 경로에 대한 인증 제외
                    .requestMatchers("/api/auth/login").permitAll() //
                    .requestMatchers("/user/**").hasRole("USER")
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            } //                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            //                .userDetailsService(jpaUserDetailsService)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
//            .userDetailsService(jpaUserDetailsService)
//            .apply(JwtTokenFilterConfig(jwtTokenProvider))

        //            .apply(new JwtSecurityConfig(tokenProvider)); // JwtSecurityConfig 적용
        return httpSecurity.build()
    }

    //    private CorsConfigurationSource getCorsConfigurationSource() {
    //        return request -> {
    //            var cors = new CorsConfiguration();
    //
    //            cors.setAllowedOrigins(List.of("*"));
    //            cors.setAllowedHeaders(List.of("*"));
    //            cors.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    //            cors.setAllowCredentials(true);
    //            return cors;
    //        };
    //    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }
}