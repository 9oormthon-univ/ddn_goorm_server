package com.ddn.goorm.api.account

import com.ddn.goorm.api.account.dto.response.AccountRes
import com.ddn.goorm.domains.account.AccountDomainService
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class AccountApiService (
    private var accountDomainService: AccountDomainService
) {
    fun findAccountList(): List<AccountRes>? {
        return accountDomainService.findAccountList().map{
            it ->  AccountRes(it)
        }.toList()
    }

}