package com.ddn.goorm.api.post.goorm

import com.ddn.goorm.api.post.goorm.dto.request.goormCreateReq
import com.ddn.goorm.api.post.goorm.dto.response.GoormRes
import com.ddn.goorm.domains.group.member.Member
import com.ddn.goorm.domains.group.topic.TopicDomainService
import com.ddn.goorm.domains.post.goorm.Goorm
import com.ddn.goorm.domains.post.goorm.GoormDomainService
import org.springframework.stereotype.Service

@Service
class GoormApiService (
    private val goormDomainService: GoormDomainService,
    private val topicDomainService: TopicDomainService
) {
    fun createGoorm(member: Member?, req: goormCreateReq) {
        goormDomainService.createGoorm(
            req.toEntity(
                topicDomainService.findById(req.topic),
                member!!
            )
        )
    }

    fun findGoormList(topic: Long): List<GoormRes>? {
        return goormDomainService.findAllByTopicId(topic)
            .stream().map {it -> GoormRes(it)}.toList()
    }

    fun updateGoormFin(id: Long) {
        val goorm: Goorm = goormDomainService.findById(id)
        goormDomainService.updateGoormFinStatus(goorm)
    }
}