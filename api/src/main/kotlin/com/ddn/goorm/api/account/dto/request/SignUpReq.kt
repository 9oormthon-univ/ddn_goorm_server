package com.ddn.goorm.api.account.dto.request

import com.ddn.goorm.domains.account.Account

data class SignUpReq (
    var email: String,
    var nickname: String,
    var password: String
) {
    fun toEntity() : Account {
        return Account(
            email = this.email,
            nickname = this.nickname,
            password = this.password
        )
    }
}