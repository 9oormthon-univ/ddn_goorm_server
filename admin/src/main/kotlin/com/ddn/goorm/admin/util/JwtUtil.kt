package com.ddn.goorm.admin.util

import com.ddn.goorm.admin.dto.TokenDto
import com.ddn.goorm.admin.dto.AccountDto
import com.ddn.goorm.common.enums.Role
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import java.security.Key
import java.time.Duration
import java.util.Date


@Component
class JwtUtil (
    @Value("\${spring.jwt.secret}") secretKey: String
) {
    private val AUTHORITIES_KEY = "role"
    private val BEARER_TYPE = "Bearer"
    private val ACCESS_TOKEN_EXPIRE_TIME: Long = Duration.ofHours(2).toMillis()
    private val REFRESH_TOKEN_EXPIRE_TIME: Long = Duration.ofDays(14).toMillis()
    private val key: Key

    init {
        val keyBytes: ByteArray = Decoders.BASE64.decode(secretKey)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun generateToken (
        email: String,
        id: Long,
        team: Long? = null,
        member: Long? = null,
        role: List<Role>
    ) : TokenDto {
        val current = Date().time

        val accessToken = Jwts.builder()
            .setSubject(id.toString())
            .claim("team", team.toString())
            .claim("member", member.toString())
            .claim(AUTHORITIES_KEY, role.stream().map { it -> it.name }.toList().joinToString(","))
            .setExpiration(Date(current + ACCESS_TOKEN_EXPIRE_TIME))
            .signWith(key, SignatureAlgorithm.HS512).compact()
        val refreshToken = Jwts.builder()
            .setSubject(email)
            .setExpiration(Date(current + REFRESH_TOKEN_EXPIRE_TIME))
            .signWith(key, SignatureAlgorithm.HS512).compact()

        return TokenDto(accessToken, refreshToken)
    }

    fun getAuthentication(accessToken: String): Authentication {
        val claims = parseClaims(accessToken)

        if (claims[AUTHORITIES_KEY] == null) {
            throw RuntimeException("권한 정보가 없는 토큰입니다.")
        }

        val authorities: Collection<GrantedAuthority> = claims[AUTHORITIES_KEY].toString().split(",")
            .map { it -> SimpleGrantedAuthority(it) }
            .toList()

        val principal: AccountDto

        if (claims.get("team") == "null") {
            principal = AccountDto(
                claims.subject.toLong(),
                null,
                null
            )
        } else {
            principal = AccountDto (
                claims.subject.toLong(),
                claims.get("team").toString().toLong(),
                claims.get("member").toString().toLong()
            )
        }

        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    private fun parseClaims(accessToken: String): Claims {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).body
        } catch (e: ExpiredJwtException) {
            throw e
        }
    }

    fun getExpiration(accessToken: String): Long {
        val expirationDate = Jwts.parserBuilder()
            .setSigningKey(key)
            .build().parseClaimsJws(accessToken)
            .body.expiration
        return expirationDate.time - Date().time
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token)
            return true
        } catch (e: ExpiredJwtException) {
            throw e
        }
    }

}