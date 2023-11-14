package com.ddn.goorm.admin.config

import com.ddn.goorm.admin.filter.JwtFilter
import com.ddn.goorm.admin.util.JwtUtil
import io.jsonwebtoken.Jwt
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtSecurityConfig (
    private val jwtUtil: JwtUtil
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

    @Override
    override fun configure(httpSecurity: HttpSecurity) {
        val jwtFilter: JwtFilter = JwtFilter(jwtUtil)
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

}