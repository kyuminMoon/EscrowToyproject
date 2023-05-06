package com.tistory.kmmoon.core.security

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import java.util.stream.Collectors


// 토큰 생성, 검증
//@Configurable
@Component
class JwtTokenProvider(@Value("\${jwt.secret}") secret: String, @Value("\${jwt.access-token-validity-in-seconds}") tokenValidityInSeconds: Long, @Value("\${jwt.refresh-token-validity-in-seconds}") refreshTokenValidityInSeconds: Long) {

  val log: Logger = LoggerFactory.getLogger(JwtTokenProvider::class.java)
  final val accessTokenValidityInMilliseconds: Long
  final val refreshTokenValidityInMilliseconds: Long
  lateinit var key: Key

  init {
    accessTokenValidityInMilliseconds = tokenValidityInSeconds * 1000
    refreshTokenValidityInMilliseconds = refreshTokenValidityInSeconds * 1000

    //시크릿 값을 decode해서 키 변수에 할당
    val keyBytes = Decoders.BASE64.decode(secret)
    key = Keys.hmacShaKeyFor(keyBytes)
  }

  // 토큰 생성
  fun createAccessToken(authentication: Authentication): String {
    val authorities = authentication.authorities.stream()
      .map { obj: GrantedAuthority -> obj.authority }
      .collect(Collectors.joining(","))
    val now = Date().time
    val validity = Date(now + accessTokenValidityInMilliseconds)
    return Jwts.builder()
      .setSubject(authentication.name)
      .claim(AUTHORITIES_KEY, authorities)
      .claim(TOKEN_TYPE_KEY, ACCESS_TOKEN_TYPE)
      .signWith(key, SignatureAlgorithm.HS256)
      .setExpiration(validity)
      .compact()
  }

  fun createRefreshToken(authentication: Authentication): String {
    val authorities = authentication.authorities.stream()
      .map { obj: GrantedAuthority -> obj.authority }
      .collect(Collectors.joining(","))
    val now = Date().time
    val validity = Date(now + refreshTokenValidityInMilliseconds)
    return Jwts.builder()
      .setSubject(authentication.name)
      .claim(AUTHORITIES_KEY, authorities)
      .claim(TOKEN_TYPE_KEY, REFRESH_TOKEN_TYPE)
      .signWith(key, SignatureAlgorithm.HS256)
      .setExpiration(validity)
      .compact()
  }

  // 토큰을 받아 클레임을 만들고 권한정보를 빼서 시큐리티 유저객체를 만들어 Authentication 객체 반환
  fun getAuthentication(token: String?): Authentication {
    val claims = Jwts
      .parserBuilder()
      .setSigningKey(key)
      .build()
      .parseClaimsJws(token)
      .body
    val authorities: Collection<GrantedAuthority> = Arrays.stream(claims[AUTHORITIES_KEY].toString().split(",".toRegex()).dropLastWhile { it.isEmpty() }
      .toTypedArray())
      .map { role: String? -> SimpleGrantedAuthority(role) }
      .collect(Collectors.toList())

    // 디비를 거치지 않고 토큰에서 값을 꺼내 바로 시큐리티 유저 객체를 만들어 Authentication을 만들어 반환하기에 유저네임, 권한 외 정보는 알 수 없다.
    val principal = User(claims.subject, "", authorities)
    return UsernamePasswordAuthenticationToken(principal, token, authorities)
  }

  // 토큰 유효성 검사
  fun validateAccessToken(token: String?): Boolean {
    try {
      val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
      val tokenType = claims.get("token_type")

      if (tokenType != null) {
        return tokenType == ACCESS_TOKEN_TYPE
      }
    } catch (e: SecurityException) {
      log.info("잘못된 JWT 서명입니다.")
      e.printStackTrace()
    } catch (e: MalformedJwtException) {
      log.info("잘못된 JWT 서명입니다.")
      e.printStackTrace()
    } catch (e: ExpiredJwtException) {
      log.info("만료된 JWT 토큰입니다.")
      e.printStackTrace()
    } catch (e: UnsupportedJwtException) {
      log.info("지원되지 않는 JWT 토큰입니다.")
      e.printStackTrace()
    } catch (e: IllegalArgumentException) {
      log.info("JWT 토큰이 잘못되었습니다.")
      e.printStackTrace()
    }
    return false
  }

  fun validateRefreshToken(token: String?): Boolean {
    try {
      val claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
      val tokenType = claims.get("token_type")

      if (tokenType != null) {
        return tokenType == REFRESH_TOKEN_TYPE
      }
      return true
    } catch (e: SecurityException) {
      log.info("잘못된 JWT 서명입니다.")
    } catch (e: MalformedJwtException) {
      log.info("잘못된 JWT 서명입니다.")
    } catch (e: ExpiredJwtException) {
      log.info("만료된 JWT 토큰입니다.")
    } catch (e: UnsupportedJwtException) {
      log.info("지원되지 않는 JWT 토큰입니다.")
    } catch (e: IllegalArgumentException) {
      log.info("JWT 토큰이 잘못되었습니다.")
    }
    return false
  }

  companion object {
    const val AUTHORITIES_KEY = "auth"
    const val TOKEN_TYPE_KEY = "token_type"
    const val ACCESS_TOKEN_TYPE = "access"
    const val REFRESH_TOKEN_TYPE = "refresh"
  }
}