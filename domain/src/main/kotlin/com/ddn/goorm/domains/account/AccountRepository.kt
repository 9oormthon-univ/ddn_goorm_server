package com.ddn.goorm.domains.account

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AccountRepository : JpaRepository<Account, Long> {
    fun existsByEmail(email: String) : Boolean
}