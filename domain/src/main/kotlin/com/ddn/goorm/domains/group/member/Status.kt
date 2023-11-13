package com.ddn.goorm.domains.group.member

enum class Status(val label: String) {
    DELETED("탈퇴"),
    ACTIVE("활성"),
    INVITED("초대")
}