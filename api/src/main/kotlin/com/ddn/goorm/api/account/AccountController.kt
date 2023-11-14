package com.ddn.goorm.api.account

import com.ddn.goorm.api.account.dto.request.SignUpReq
import com.ddn.goorm.api.account.dto.response.AccountRes
import com.ddn.goorm.common.response.ResponseCode
import com.ddn.goorm.common.response.SuccessResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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

    @PostMapping("/signup")
    fun accountCreateSignUp(@RequestBody req: SignUpReq) : ResponseEntity<SuccessResponse> {
        var res: AccountRes? = accountApiService.createAccount(req)
        return ResponseEntity(
            SuccessResponse(
                status = ResponseCode.CREATED.status,
                code = ResponseCode.CREATED.code,
                message = "회원가입에 성공하였습니다."
            ),
            HttpStatus.CREATED
        )
    }

}