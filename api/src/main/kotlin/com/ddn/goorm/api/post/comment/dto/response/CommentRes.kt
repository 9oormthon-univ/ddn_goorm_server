package com.ddn.goorm.api.post.comment.dto.response

import com.ddn.goorm.api.account.dto.response.AccountRes
import com.ddn.goorm.domains.post.comment.Comment

class CommentRes (
   comment: Comment
) {
    val id = comment.id
    val contents = comment.contents
    val author = AccountRes(comment.member!!.account!!)
}