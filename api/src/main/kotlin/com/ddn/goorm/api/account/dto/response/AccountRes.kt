package com.ddn.goorm.api.account.dto.response

import com.ddn.goorm.domains.account.Account

class AccountRes (
    entity: Account
) {
    var id: Long? = entity.id
    var email: String? = entity.email
    var nickname: String? = entity.nickname
}