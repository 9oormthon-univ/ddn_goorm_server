package com.ddn.goorm.api.account

import com.ddn.goorm.admin.service.JwtService
import com.ddn.goorm.admin.service.PasswordService
import com.ddn.goorm.api.account.dto.request.SignInReq
import com.ddn.goorm.api.account.dto.request.SignUpReq
import com.ddn.goorm.api.account.dto.response.AccountRes
import com.ddn.goorm.api.account.dto.response.TokenRes
import com.ddn.goorm.common.enums.Role
import com.ddn.goorm.domains.account.Account
import com.ddn.goorm.domains.account.AccountDomainService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class AccountApiService (
    private val accountDomainService: AccountDomainService,
    private val passwordService: PasswordService,
    private val jwtService: JwtService
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

    fun findAccountSignIn(req: SignInReq): TokenRes {
        val entity: Account = accountDomainService.findByEmail(req.email)
        if (!passwordService.isValidPassword(req.password, entity.password!!)) {
            throw IllegalArgumentException("비밀번호가 일치하지 않습니다.")
        }
        val role: List<Role> = listOf(Role.ROLE_GUEST)
        return TokenRes(
            jwtService.createToken(email=entity.email!!, id=entity.id!!, role=role),
            "로그인에 성공하였습니다. 팀에 대한 권한은 아직 없는 상태.",
            entity
        )
    }

}