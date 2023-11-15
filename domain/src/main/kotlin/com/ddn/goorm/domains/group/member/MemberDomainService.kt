package com.ddn.goorm.domains.group.member

import com.ddn.goorm.common.enums.Role
import com.ddn.goorm.domains.account.Account
import com.ddn.goorm.domains.group.team.Team
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class MemberDomainService(
    private val memberRepository: MemberRepository
) {
    @Transactional(readOnly = true)
    fun findMemberByAccountAndTeamId(account: Account, team: Long): Member {
        return memberRepository.findDistinctByAccountAndTeam_Id(account, team)
            .orElseThrow{IllegalArgumentException("가입하지 않은 팀입니다.")};
    }

    @Transactional(readOnly = true)
    fun findAllByAccount(account: Account) : List<Member> {
        return memberRepository.findAllByAccount(account)
    }

    fun createMember(team: Team, account: Account, role: Role): Member {
        return memberRepository.save(Member(team=team, account=account, role=role))
    }

    fun existsByLeaderAndTeamId(account: Account, team: Long, role: Role? = Role.ROLE_LEADER): Boolean {
        return memberRepository.existsByAccountAndTeam_IdAndRole(account, team, role!!)
    }

    fun findMemberByTeam(team: Long): List<Member> {
        return memberRepository.findAllByTeam_Id(team)
    }

    fun deleteTeamMemberById(team: Long, member: Long) {
        val member: Member = memberRepository.findById(member)
            .orElseThrow {IllegalArgumentException("존재하지 않는 멤버입니다.")}
        member.delete()
        memberRepository.save(member)
    }
}