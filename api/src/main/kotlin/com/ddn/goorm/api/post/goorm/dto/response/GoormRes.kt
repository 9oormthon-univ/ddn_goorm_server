package com.ddn.goorm.api.post.goorm.dto.response

import com.ddn.goorm.api.account.dto.response.AccountRes
import com.ddn.goorm.domains.post.goorm.Goorm

class GoormRes (
    goorm: Goorm
) {
    val id: Long? = goorm.id
    val title: String? = goorm.title
    val contents: String? = goorm.contents
    val account: AccountRes = AccountRes(goorm.member!!.account!!)
}
