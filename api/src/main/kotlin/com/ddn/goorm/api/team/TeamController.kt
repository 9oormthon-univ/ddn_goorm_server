package com.ddn.goorm.api.team

import com.ddn.goorm.admin.annotation.AuthAccount
import com.ddn.goorm.api.account.dto.response.TokenRes
import com.ddn.goorm.api.team.dto.request.TeamJoinReq
import com.ddn.goorm.api.team.dto.response.TeamJoinRes
import com.ddn.goorm.api.team.dto.response.TeamRes
import com.ddn.goorm.domains.account.Account
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/teams")
class TeamController (
    private val teamApiService: TeamApiService
) {
    @PostMapping("/join")
    fun teamAuthenticationCreate (
        @AuthAccount account: Account,
        @RequestBody req: TeamJoinReq
    ) : ResponseEntity<TeamJoinRes> {
        return ResponseEntity (
            teamApiService.createTeamAuthenticateToken(account, req.team),
            HttpStatus.CREATED
        )
    }

    @GetMapping
    fun teamListFindByAccount (
        @AuthAccount account: Account
    ) : ResponseEntity<List<TeamRes>> {
        return ResponseEntity.ok(teamApiService.findTeamList(account))
    }
}