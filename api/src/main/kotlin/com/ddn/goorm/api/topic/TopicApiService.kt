package com.ddn.goorm.api.topic

import com.ddn.goorm.api.topic.dto.response.TopicRes
import com.ddn.goorm.domains.group.team.TeamDomainService
import com.ddn.goorm.domains.group.topic.TopicDomainService
import org.springframework.stereotype.Service

@Service
class TopicApiService (
    private val topicDomainService: TopicDomainService,
    private val teamDomainService: TeamDomainService
) {
    fun findTopicList(team: Long): List<TopicRes> {
        return topicDomainService.findTopicListByTeam(team)
            .stream().map { it -> TopicRes(it) }.toList()
    }
}