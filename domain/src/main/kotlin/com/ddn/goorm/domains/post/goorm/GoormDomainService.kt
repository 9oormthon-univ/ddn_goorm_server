package com.ddn.goorm.domains.post.goorm

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GoormDomainService (
    private val goormRepository: GoormRepository
) {
}