package com.ddn.goorm.api.account

import com.ddn.goorm.admin.service.PasswordService
import com.ddn.goorm.api.account.dto.request.SignUpReq
import com.ddn.goorm.api.account.dto.response.AccountRes
import com.ddn.goorm.domains.account.Account
import com.ddn.goorm.domains.account.AccountDomainService
import org.springframework.stereotype.Service

@Service
class AccountApiService (
    private val accountDomainService: AccountDomainService,
    private val passwordService: PasswordService
) {
    fun findAccountList(): List<AccountRes>? {
        return accountDomainService.findAccountList().map{
            it ->  AccountRes(it)
        }.toList()
    }

    fun createAccount(req: SignUpReq): AccountRes? {
        val entity: Account = accountDomainService.createAccount(req.toEntity(passwordService.encryptPassword(req.password)));
        return AccountRes(entity)
    }

}