package com.ddn.goorm.domains.group.topic

import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository : JpaRepository<Topic, Long> {

    fun findAllByTeam_Id(team: Long): List<Topic>

}