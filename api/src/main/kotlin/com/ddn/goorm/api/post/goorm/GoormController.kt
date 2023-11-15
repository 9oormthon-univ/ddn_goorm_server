package com.ddn.goorm.api.post.goorm

import com.ddn.goorm.admin.annotation.AuthAccount
import com.ddn.goorm.admin.annotation.AuthAccountInfo
import com.ddn.goorm.api.post.goorm.dto.request.goormCreateReq
import com.ddn.goorm.api.post.goorm.dto.response.GoormRes
import com.ddn.goorm.common.response.ResponseCode
import com.ddn.goorm.common.response.SuccessResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/goorms")
class GoormController (
    private val goormApiService: GoormApiService
) {
    @PostMapping
    fun goormCreate(
        @AuthAccount accontInfo: AuthAccountInfo,
        @RequestBody req: goormCreateReq
    ): ResponseEntity<SuccessResponse> {
        goormApiService.createGoorm(accontInfo.member, req)
        return ResponseEntity(
            SuccessResponse(ResponseCode.CREATED.code, ResponseCode.CREATED.status, "구름이 등록되었습니다."),
            HttpStatus.CREATED
        )
    }

    @GetMapping("/{topic}")
    fun goormListFindByTopic(
        @PathVariable topic: Long
    ): ResponseEntity<List<GoormRes>> {
        return ResponseEntity.ok(
            goormApiService.findGoormList(topic)
                ?.sortedByDescending { it -> it.commentCount }
                ?.sortedByDescending { it -> it.createdAt },
        )
    }


}