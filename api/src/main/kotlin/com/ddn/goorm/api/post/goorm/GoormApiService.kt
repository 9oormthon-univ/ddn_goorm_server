package com.ddn.goorm.api.post.goorm

import com.ddn.goorm.domains.post.goorm.GoormDomainService
import org.springframework.stereotype.Service

@Service
class GoormApiService (
    private val goormDomainService: GoormDomainService
) {
}