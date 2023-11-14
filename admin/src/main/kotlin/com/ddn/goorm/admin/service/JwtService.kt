package com.ddn.goorm.admin.service

import com.ddn.goorm.admin.dto.TokenDto
import com.ddn.goorm.admin.util.JwtUtil
import com.ddn.goorm.common.enums.Role
import org.springframework.stereotype.Service

@Service
class JwtService (
    private val jwtUtil: JwtUtil,
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