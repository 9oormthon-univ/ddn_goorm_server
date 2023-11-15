package com.ddn.goorm.admin.config


import com.ddn.goorm.admin.util.JwtUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val jwtUtil: JwtUtil
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.cors().configurationSource(corsConfigurationSource())
            .and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/accounts/info").hasRole("GUEST")
            .antMatchers("/teams", "/teams/join", "teams/member/{team}").hasRole("GUEST")
            .antMatchers(HttpMethod.GET, "/teams/member/{team}").hasRole("GUEST")
            .antMatchers(HttpMethod.DELETE, "/teams/member/{team}").hasRole("MEMBER")
            .antMatchers("/teams/invite", "/teams/member/{team}/{member}").hasRole("LEADER")
            .antMatchers(HttpMethod.GET, "/topics/**").hasRole("MEMBER")
            .antMatchers(HttpMethod.GET, "/topics/**").hasRole("LEADER")
            .antMatchers(HttpMethod.DELETE, "/topics/**").hasRole("LEADER")
            .antMatchers(HttpMethod.POST, "/topics/**").hasRole("LEADER")
            .anyRequest().permitAll()
            .run {
                JwtSecurityConfig(jwtUtil).configure(http)
            }

        http.headers().frameOptions().disable()
        http.headers().xssProtection().disable()

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(
    ) : CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("http://localhost:3000", "http://localhost:8080")
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
        configuration.allowedHeaders =
            listOf("Origin", "X-Requested-With", "Content-Type", "Authorization", "Oauth-Token")
        configuration.maxAge = 3000L
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}
