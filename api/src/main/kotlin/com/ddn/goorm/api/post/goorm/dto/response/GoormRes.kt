package com.ddn.goorm.api.post.goorm.dto.response

import com.ddn.goorm.api.account.dto.response.AccountRes
import com.ddn.goorm.api.post.comment.dto.response.CommentRes
import com.ddn.goorm.domains.post.goorm.Goorm
import java.time.LocalDateTime

class GoormRes (
    goorm: Goorm
) {
    val id: Long? = goorm.id
    val title: String? = goorm.title
    val contents: String? = goorm.contents
    val account: AccountRes = AccountRes(goorm.member!!.account!!)
    val isPin: Boolean? = goorm.isPin
    val commentCount: Int = goorm.comments?.size!!
    val color: Int = priority(commentCount)
    val createdAt: LocalDateTime? = goorm.createdAt
    val comments: List<CommentRes> = goorm.comments!!.stream().map { it -> CommentRes(it) }.toList()

    private fun priority(commentCount: Int): Int {
        return if (commentCount > 10) {
            1
        } else if (commentCount > 5) {
            2
        } else {
            3
        }
    }
}
