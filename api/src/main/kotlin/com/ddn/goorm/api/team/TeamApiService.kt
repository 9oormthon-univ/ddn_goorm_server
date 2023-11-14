package com.ddn.goorm.api.team

import com.ddn.goorm.admin.service.JwtService
import com.ddn.goorm.api.account.dto.response.TokenRes
import com.ddn.goorm.api.member.dto.response.MemberRes
import com.ddn.goorm.api.team.dto.response.TeamJoinRes
import com.ddn.goorm.api.team.dto.response.TeamRes
import com.ddn.goorm.common.enums.Role
import com.ddn.goorm.domains.account.Account
import com.ddn.goorm.domains.group.member.Member
import com.ddn.goorm.domains.group.member.MemberDomainService
import com.ddn.goorm.domains.group.team.TeamDomainService
import org.springframework.stereotype.Service

@Service
class TeamApiService (
    private val jwtService: JwtService,
    private val teamDomainService: TeamDomainService,
    private val memberDomainService: MemberDomainService
) {
    fun createTeamAuthenticateToken(account: Account, team: Long): TeamJoinRes {
        val member: Member = memberDomainService.findMemberByAccountAndTeamId(account, team)
        return TeamJoinRes(
            TokenRes(
                jwtService.createToken(
                    email = account.email!!,
                    team = team,
                    id = account.id!!,
                    member = member.id,
                    role = listOf(Role.ROLE_GUEST, member.role!!)
                ),
                "",
                account
            ),
            TeamRes(member.team!!),
            MemberRes(member)
        )
    }
}