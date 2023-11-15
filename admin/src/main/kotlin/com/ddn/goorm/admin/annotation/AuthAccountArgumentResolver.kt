package com.ddn.goorm.admin.annotation

import com.ddn.goorm.admin.dto.AccountDto
import com.ddn.goorm.domains.account.Account
import com.ddn.goorm.domains.account.AccountDomainService
import com.ddn.goorm.domains.group.member.MemberDomainService
import com.ddn.goorm.domains.group.team.TeamDomainService
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class AuthAccountArgumentResolver(
    private val accountDomainService: AccountDomainService,
    private val memberDomainService: MemberDomainService
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(AuthAccount::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): AuthAccountInfo? {
        val authAccountAnnotation: AuthAccount? = parameter.getMethodAnnotation(AuthAccount::class.java)
        val authentication = SecurityContextHolder.getContext().authentication
        val principal: AccountDto = authentication.principal as AccountDto

        if (authentication.principal == "anonymousUser") {
            return null
        }
        else {
            return if (principal.team == null) {
                AuthAccountInfo(
                    accountDomainService.findById(principal.account!!)!!, null
                )
            } else {
                if (!memberDomainService.existCheck(principal.account!!, principal.member!!, principal.team)) {
                    throw IllegalArgumentException("권한이 없습니다.")
                }
                AuthAccountInfo(
                    accountDomainService.findById(principal.account)!!,
                    memberDomainService.findById(principal.member)
                )
            }
        }
    }
}