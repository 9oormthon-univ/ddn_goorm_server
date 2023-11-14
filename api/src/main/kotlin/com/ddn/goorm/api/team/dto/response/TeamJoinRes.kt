package com.ddn.goorm.api.team.dto.response

import com.ddn.goorm.api.account.dto.response.TokenRes
import com.ddn.goorm.api.member.dto.response.MemberRes


class TeamJoinRes(
    val tokenInfo: TokenRes,
    val team: TeamRes,
    val member: MemberRes,
)