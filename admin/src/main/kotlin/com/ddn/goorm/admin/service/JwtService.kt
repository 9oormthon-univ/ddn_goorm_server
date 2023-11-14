package com.ddn.goorm.admin.service

import com.ddn.goorm.admin.dto.TokenDto
import com.ddn.goorm.admin.util.JwtUtil
import com.ddn.goorm.domains.group.member.Role
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class JwtService (
    val jwtUtil: JwtUtil,
    val authenticationManagerBuilder: AuthenticationManagerBuilder
) {
    fun createToken (
        email: String,
        id: Long,
        team: Long? = null,
        member: Long? = null,
        role: List<Role>
    ): TokenDto {
        return jwtUtil.generateToken(email, id, team, member, role)
    }
}