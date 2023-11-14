package com.ddn.goorm.api.team.dto.request

import com.ddn.goorm.domains.group.team.Team

data class TeamCreateReq (
    val name: String,
    val detail: String,
    val icon: String
) {
    fun toEntity() : Team {
        return Team(this.name, this.detail, this.icon)
    }
}