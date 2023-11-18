package com.ddn.goorm.domains.post.comment

import com.ddn.goorm.domains.post.goorm.Goorm
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findAllByGoorm_Id(goormId: Long): List<Comment>
}