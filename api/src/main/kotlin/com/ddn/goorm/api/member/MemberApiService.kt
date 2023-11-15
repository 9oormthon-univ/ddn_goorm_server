package com.ddn.goorm.api.member

import com.ddn.goorm.api.member.dto.response.MemberRes
import com.ddn.goorm.api.team.dto.request.TeamCreateReq
import com.ddn.goorm.common.enums.Role
import com.ddn.goorm.domains.account.Account
import com.ddn.goorm.domains.group.member.Member
import com.ddn.goorm.domains.group.member.MemberDomainService
import com.ddn.goorm.domains.group.team.Team
import org.springframework.stereotype.Service

@Service
class MemberApiService (
    private val memberDomainService: MemberDomainService
) {
    fun createMember(account: Account, team: Team, role: Role): Member {
        return memberDomainService.createMember(team, account, role)
    }

    fun existsByLeaderAndTeamId(account: Account, team: Long): Boolean {
        return memberDomainService.existsByLeaderAndTeamId(account, team)
    }

    fun findMemberByTeam(team: Long): List<MemberRes> {
        return memberDomainService.findMemberByTeam(team)
            .stream().map {it -> MemberRes(it)}.toList()
    }

    fun deleteMember(team: Long, member: Long) {
        memberDomainService.deleteTeamMemberById(team, member)
    }

    fun deleteMemberSelf(team: Long, account: Account) {
        memberDomainService.deleteTeamMemberById (
            team,
            memberDomainService.findMemberByAccountAndTeamId(account, team).id!!
        )
    }
}