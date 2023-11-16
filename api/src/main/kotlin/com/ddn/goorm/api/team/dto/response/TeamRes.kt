package com.ddn.goorm.api.team.dto.response

import com.ddn.goorm.api.topic.dto.response.TopicRes
import com.ddn.goorm.domains.group.team.Team
import kotlin.streams.toList

class TeamRes (
    team: Team
) {
    val id = team.id
    val name = team.name
    val detail = team.detail
    val icon = team.icon
    val status = team.status
    val topics = team.topics!!.stream().map { it -> TopicRes(it) }.toList()
}