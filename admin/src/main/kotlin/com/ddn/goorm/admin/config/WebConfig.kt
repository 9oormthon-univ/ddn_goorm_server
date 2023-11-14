package com.ddn.goorm.admin.config

import com.ddn.goorm.admin.annotation.AuthAccountArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig (
    private val authAccountArgumentResolver: AuthAccountArgumentResolver
): WebMvcConfigurer {
    @Override
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        // super.addArgumentResolvers(resolvers)
        resolvers.add(authAccountArgumentResolver)
    }
}