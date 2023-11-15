package com.ddn.goorm.api.topic

import com.ddn.goorm.admin.annotation.AuthAccount
import com.ddn.goorm.api.topic.dto.response.TopicRes
import com.ddn.goorm.domains.account.Account
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topics")
class TopicController (
    private val topicApiService: TopicApiService
) {

    @GetMapping("/{team}")
    fun topicListTeamFind (
        @AuthAccount account: Account,
        @PathVariable  team: Long
    ) : ResponseEntity<List<TopicRes>> {
        return ResponseEntity.ok(topicApiService.findTopicList(team))
    }


}