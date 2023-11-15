package com.ddn.goorm.api.team.dto.response

import com.ddn.goorm.domains.group.team.Team

class TeamRes (
    team: Team
) {
    val id = team.id
    val name = team.name
    val detail = team.detail
    val icon = team.icon
    val status = team.status
}