package com.ddn.goorm.api.account.dto.request

import com.ddn.goorm.domains.account.Account

data class SignUpReq (
    val email: String,
    val nickname: String,
    val password: String
) {
    fun toEntity(encryptPassword: String) : Account {
        return Account(
            email = this.email,
            nickname = this.nickname,
            password = encryptPassword
        )
    }
}