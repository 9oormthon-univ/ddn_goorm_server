package com.ddn.goorm.api.member.dto.response

import com.ddn.goorm.domains.group.member.Member

class MemberRes(
    member: Member
) {
    val id = member.id
    val status = member.status
    val role = member.role
}