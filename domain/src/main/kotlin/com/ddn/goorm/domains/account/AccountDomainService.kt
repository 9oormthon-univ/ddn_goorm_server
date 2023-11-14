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

    fun createAccount(entity: Account) : Account {
        if (accountRepository.existsByEmail(entity.email!!)) {
            throw IllegalArgumentException("중복되는 이메일이 존재합니다.")
        }
        return accountRepository.save(entity);
    }
}