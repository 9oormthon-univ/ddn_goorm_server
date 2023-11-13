package com.ddn.goorm.domains.post.comment

import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
}