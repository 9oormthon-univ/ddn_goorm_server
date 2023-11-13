package com.ddn.goorm.api.account

import com.ddn.goorm.api.account.dto.response.AccountRes
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountController (
    private var accountApiService: AccountApiService
) {

    @GetMapping("/test")
    fun accountFindTest() : ResponseEntity<List<AccountRes>> {
        return ResponseEntity.ok(accountApiService.findAccountList())
    }

}