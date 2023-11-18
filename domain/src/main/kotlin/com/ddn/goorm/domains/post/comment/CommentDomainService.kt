package com.ddn.goorm.domains.post.comment

import com.ddn.goorm.domains.group.member.Member
import com.ddn.goorm.domains.post.goorm.Goorm
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CommentDomainService (
    private val commentRepository: CommentRepository
) {
    fun createComment(member: Member, goorm: Goorm, contents: String): Comment {
        return commentRepository.save(Comment(
            contents=contents, goorm=goorm, member=member
        ))
    }

    @Transactional(readOnly = true)
    fun findCommentListByGoormId(goormId: Long) : List<Comment> {
        return commentRepository.findAllByGoorm_Id(goormId);
    }
}