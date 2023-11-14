package com.ddn.goorm.domains.group.member

import com.ddn.goorm.domains.account.Account
import com.ddn.goorm.domains.group.team.TeamRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class MemberDomainService(
    private val memberRepository: MemberRepository
) {
    @Transactional(readOnly = true)
    fun findMemberByAccountAndTeamId(account: Account, team: Long): Member {
        return memberRepository.findMemberByAccountAndTeam_Id(account, team)
            .orElseThrow{IllegalArgumentException("가입하지 않은 팀입니다.")};
    }
}