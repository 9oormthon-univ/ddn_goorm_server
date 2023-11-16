package com.ddn.goorm.api.post.goorm

import com.ddn.goorm.admin.annotation.AuthAccount
import com.ddn.goorm.admin.annotation.AuthAccountInfo
import com.ddn.goorm.api.member.MemberApiService
import com.ddn.goorm.api.post.goorm.dto.request.GoormCreateGuestReq
import com.ddn.goorm.common.response.ResponseCode
import com.ddn.goorm.common.response.SuccessResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/guest/goorms")
class GoormGuestController (
    private val goormApiService: GoormApiService,
    private val memberApiService: MemberApiService
) {
    @PostMapping
    fun goormCreateByGuestAccount(
        @AuthAccount account: AuthAccountInfo,
        @RequestBody req: GoormCreateGuestReq
    ): ResponseEntity<SuccessResponse> {
        goormApiService.createGoormByGuest(
            memberApiService.findMemberByTeamIdAndAccount(req.team, account.account),
            req
        )
        return ResponseEntity(
            SuccessResponse(ResponseCode.CREATED.code, ResponseCode.CREATED.status, "구름이 등록되었습니다."),
            HttpStatus.CREATED
        )
    }
 }