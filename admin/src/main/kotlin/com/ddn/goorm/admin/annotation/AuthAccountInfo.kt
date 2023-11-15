package com.ddn.goorm.admin.annotation

import com.ddn.goorm.domains.account.Account
import com.ddn.goorm.domains.group.member.Member
import com.ddn.goorm.domains.group.team.Team

data class AuthAccountInfo (
    val account: Account,
    val member: Member?
)