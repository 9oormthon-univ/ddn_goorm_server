package com.ddn.goorm.api.post.comment

import com.ddn.goorm.admin.annotation.AuthAccount
import com.ddn.goorm.admin.annotation.AuthAccountInfo
import com.ddn.goorm.api.post.comment.dto.request.CommentCreateReq
import com.ddn.goorm.common.response.ResponseCode
import com.ddn.goorm.common.response.SuccessResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/comments")
class CommentApiController (
    private val commentApiService: CommentApiService
) {
    @PostMapping
    fun createComment(
        @AuthAccount accountInfo: AuthAccountInfo,
        @RequestBody commentCreateReq: CommentCreateReq
    ): ResponseEntity<SuccessResponse> {
        commentApiService.createComment(accountInfo.member!!, commentCreateReq);
        return ResponseEntity(
            SuccessResponse(
                status = ResponseCode.CREATED.status,
                code = ResponseCode.CREATED.code,
                message = "타래가 생성되었습니다."
            ),
            HttpStatus.CREATED
        )
    }

}