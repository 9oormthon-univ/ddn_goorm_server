package com.ddn.goorm.domains.account

import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class AccountDomainService (
    var accountRepository: AccountRepository
) {
    fun findAccountList() : List<Account> {
        return accountRepository.findAll()
    }
}