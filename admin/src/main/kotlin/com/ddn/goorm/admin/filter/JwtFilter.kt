package com.ddn.goorm.admin.filter

import com.ddn.goorm.admin.util.JwtUtil
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtFilter (
    private val jwtUtil: JwtUtil
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val accessToken: String? = resolveToken(request)

        if (StringUtils.hasText(accessToken) && jwtUtil.validateToken(accessToken!!)) {
            val authentication: Authentication = jwtUtil.getAuthentication(accessToken)
            val context: SecurityContext = SecurityContextHolder.createEmptyContext()
            context.authentication = authentication
            SecurityContextHolder.setContext(context)
        }

        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest) : String? {
        val bearerToken: String = request.getHeader("Authorization")

        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            bearerToken.substring(7)
        } else {
            null
        }
    }
}