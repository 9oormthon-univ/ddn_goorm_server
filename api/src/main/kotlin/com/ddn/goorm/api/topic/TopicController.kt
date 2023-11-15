package com.ddn.goorm.api.topic

import com.ddn.goorm.admin.annotation.AuthAccount
import com.ddn.goorm.admin.annotation.AuthAccountInfo
import com.ddn.goorm.api.topic.dto.request.TopicCreateReq
import com.ddn.goorm.api.topic.dto.response.TopicRes
import com.ddn.goorm.common.response.ResponseCode
import com.ddn.goorm.common.response.SuccessResponse
import com.ddn.goorm.domains.account.Account
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topics")
class TopicController (
    private val topicApiService: TopicApiService
) {

    @GetMapping("/{team}")
    fun topicListTeamFind (
        @AuthAccount accountInfo: AuthAccountInfo,
        @PathVariable team: Long
    ) : ResponseEntity<List<TopicRes>> {
        return ResponseEntity.ok(topicApiService.findTopicList(team))
    }

    @PostMapping
    fun topicCreate (
        @AuthAccount accountInfo: AuthAccountInfo,
        @RequestBody req: TopicCreateReq
    ) : ResponseEntity<SuccessResponse> {
        topicApiService.createTopic(req)
        return ResponseEntity(
            SuccessResponse(
                code = ResponseCode.CREATED.code,
                status = ResponseCode.CREATED.status,
                message = "새 주제가 생성되었습니다."
            ),
            HttpStatus.CREATED
        )
    }

}