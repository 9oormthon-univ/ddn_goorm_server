package com.ddn.goorm.api.team

import com.ddn.goorm.admin.annotation.AuthAccount
import com.ddn.goorm.admin.annotation.AuthAccountInfo
import com.ddn.goorm.api.account.AccountApiService
import com.ddn.goorm.api.account.dto.response.TokenRes
import com.ddn.goorm.api.member.MemberApiService
import com.ddn.goorm.api.member.dto.response.MemberRes
import com.ddn.goorm.api.team.dto.request.TeamCreateReq
import com.ddn.goorm.api.team.dto.request.TeamInviteReq
import com.ddn.goorm.api.team.dto.request.TeamJoinReq
import com.ddn.goorm.api.team.dto.response.TeamJoinRes
import com.ddn.goorm.api.team.dto.response.TeamRes
import com.ddn.goorm.common.enums.Role
import com.ddn.goorm.common.response.ResponseCode
import com.ddn.goorm.common.response.SuccessResponse
import com.ddn.goorm.domains.account.Account
import com.ddn.goorm.domains.group.member.Member
import com.ddn.goorm.domains.group.team.Team
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/teams")
class TeamController (
    private val teamApiService: TeamApiService,
    private val memberApiService: MemberApiService,
    private val accountApiService: AccountApiService
) {
    @PostMapping("/join")
    fun teamAuthenticationCreate (
        @AuthAccount accountInfo: AuthAccountInfo,
        @RequestBody req: TeamJoinReq
    ) : ResponseEntity<TeamJoinRes> {
        return ResponseEntity (
            teamApiService.createTeamAuthenticateToken(accountInfo.account, req.team),
            HttpStatus.CREATED
        )
    }

    @GetMapping
    fun teamListFindByAccount (
        @AuthAccount accountInfo: AuthAccountInfo
    ) : ResponseEntity<List<TeamRes>> {
        return ResponseEntity.ok(teamApiService.findTeamList(accountInfo.account))
    }

    @GetMapping("/member/{team}")
    fun teamMemberListFind (
        @AuthAccount accountInfo: AuthAccountInfo,
        @PathVariable team: Long
    ) : ResponseEntity<List<MemberRes>> {
        return ResponseEntity.ok(memberApiService.findMemberByTeam(team))
    }

    @DeleteMapping("/member/{team}/{member}")
    fun teamMemberDelete (
        @AuthAccount accountInfo: AuthAccountInfo,
        @PathVariable team: Long,
        @PathVariable member: Long
    ) : ResponseEntity<SuccessResponse> {
        memberApiService.deleteMember(team, member)
        return ResponseEntity (
            SuccessResponse(ResponseCode.OK.code, ResponseCode.OK.status, "멤버를 비활성화했습니다."),
            HttpStatus.OK
        )
    }

    @DeleteMapping("/member/{team}")
    fun teamMemberSelfDelete (
        @AuthAccount accountInfo: AuthAccountInfo,
        @PathVariable team: Long
    ) : ResponseEntity<SuccessResponse> {

        memberApiService.deleteMemberSelf(team, accountInfo.account)

        return ResponseEntity (
            SuccessResponse(ResponseCode.OK.code, ResponseCode.OK.status, "멤버를 비활성화했습니다."),
            HttpStatus.OK
        )
    }

    @PostMapping
    fun teamCreate (
        @AuthAccount accountInfo: AuthAccountInfo,
        @RequestBody req: TeamCreateReq
    ) : ResponseEntity<SuccessResponse> {
        val team: Team = teamApiService.createTeam(accountInfo.account, req)
        memberApiService.createMember(accountInfo.account, team, Role.ROLE_LEADER)
        return ResponseEntity (
            SuccessResponse(ResponseCode.CREATED.code, ResponseCode.CREATED.status, team.id.toString()),
            HttpStatus.CREATED
        )
    }

    @PostMapping("/invite")
    fun teamMemberCreateByAccount (
        @AuthAccount accountInfo: AuthAccountInfo,
        @RequestBody req: TeamInviteReq
    ): ResponseEntity<SuccessResponse> {
        memberApiService.createMember(
            accountApiService.findAccountByEmail(req.email),
            accountInfo.member?.team!!,
            Role.ROLE_MEMBER
        )

        return ResponseEntity (
            SuccessResponse(ResponseCode.CREATED.code, ResponseCode.CREATED.status, "팀에 멤버를 초대하였습니다."),
            HttpStatus.CREATED
        )
    }


}