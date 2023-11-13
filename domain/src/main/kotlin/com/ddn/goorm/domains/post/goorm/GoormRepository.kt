package com.ddn.goorm.domains.post.goorm

import org.springframework.data.jpa.repository.JpaRepository

interface GoormRepository : JpaRepository<Goorm, Long> {
}