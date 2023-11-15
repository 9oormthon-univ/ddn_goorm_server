package com.ddn.goorm.api.topic.dto.request

import com.ddn.goorm.domains.group.team.Team
import com.ddn.goorm.domains.group.topic.Topic


data class TopicCreateReq (
    val name: String,
    val detail: String,
    val team: Long
) {
    fun toEntity(team: Team): Topic {
        return Topic(
            name = name,
            detail = detail,
            team = team
        )
    }
}
