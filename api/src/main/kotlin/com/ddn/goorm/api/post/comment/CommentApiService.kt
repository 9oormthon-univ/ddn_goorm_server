package com.ddn.goorm.api.post.comment

import com.ddn.goorm.api.post.comment.dto.request.CommentCreateReq
import com.ddn.goorm.domains.group.member.Member
import com.ddn.goorm.domains.post.comment.Comment
import com.ddn.goorm.domains.post.comment.CommentDomainService
import com.ddn.goorm.domains.post.goorm.GoormDomainService
import org.springframework.stereotype.Service

@Service
class CommentApiService (
    private val commentDomainService: CommentDomainService,
    private val goormDomainService: GoormDomainService
) {
    fun createComment(member: Member, req: CommentCreateReq) {
        val comment: Comment = commentDomainService.createComment(
            member, goormDomainService.findById(req.goorm), req.contents);
    }
}