package com.ddn.goorm.domains.group.member

import com.ddn.goorm.common.enums.Role
import com.ddn.goorm.domains.account.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository : JpaRepository<Member, Long> {
    fun findDistinctByAccountAndTeam_Id(account: Account, team: Long): Optional<Member>

    fun findAllByTeam_Id(team: Long): List<Member>

    fun findByAccount_IdAndTeam_Id(account: Long, team: Long): Optional<Member>

    fun findAllByAccount(account: Account): List<Member>

    fun existsByAccountAndTeam_IdAndRole(account: Account, team: Long, role: Role): Boolean

}