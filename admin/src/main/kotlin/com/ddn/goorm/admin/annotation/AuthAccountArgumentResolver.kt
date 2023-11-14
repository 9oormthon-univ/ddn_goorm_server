package com.ddn.goorm.admin.annotation

import com.ddn.goorm.domains.account.Account
import com.ddn.goorm.domains.account.AccountDomainService
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class AuthAccountArgumentResolver(
    private val accountDomainService: AccountDomainService
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(AuthAccount::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Account? {
        val authAccountAnnotation: AuthAccount = parameter.getMethodAnnotation(AuthAccount::class.java)!!
        val authentication = SecurityContextHolder.getContext().authentication

        return if (authentication.principal == "anonymousUser") {
            null
        }
        else {
            accountDomainService.findById(authentication.name.toLong())
        }


    }
}