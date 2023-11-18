package com.ddn.goorm.admin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter


@Configuration
class CorsConfig {
    @Bean
    fun securityCorsFilter(): CorsFilter {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()
        config.allowCredentials = true
        config.addAllowedOrigin("http://localhost:3000")
        config.addAllowedOrigin("http://localhost:3001")
        config.addAllowedOrigin("https://ddngoorm.xyz/")
        config.addAllowedOrigin("https://www.ddngoorm.xyz")
        config.addAllowedOrigin("chrome-extension://mbpielimoidogjhchdiedeocmofccbnp")
        config.addAllowedHeader("*")
        config.addAllowedMethod("*")
        source.registerCorsConfiguration("/**", config)
        return CorsFilter(source)
    }
}