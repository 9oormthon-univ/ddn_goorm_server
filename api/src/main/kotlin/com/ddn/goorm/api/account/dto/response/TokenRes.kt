package com.ddn.goorm.api.account.dto.response

import com.ddn.goorm.admin.dto.TokenDto
import com.ddn.goorm.domains.account.Account

class TokenRes (
    tokenDto: TokenDto,
    val message: String,
    account: Account
) {
    val accessToken: String = tokenDto.accessToken
    val refreshToken: String = tokenDto.refreshToken
    val accountInfo: AccountRes = AccountRes(account)
}