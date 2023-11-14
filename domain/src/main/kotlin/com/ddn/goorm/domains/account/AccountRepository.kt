package com.ddn.goorm.domains.account

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*
import javax.swing.text.html.Option

interface AccountRepository : JpaRepository<Account, Long> {
    fun existsByEmail(email: String) : Boolean
    fun findByEmail(email: String): Optional<Account>
}