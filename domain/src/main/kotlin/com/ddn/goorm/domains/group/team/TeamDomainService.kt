package com.ddn.goorm.domains.group.team

import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class TeamDomainService (
    private val teamRepository: TeamRepository
) {
    fun createTeam(entity: Team): Team {
        return teamRepository.save(entity)
    }

    fun findById(id: Long): Team {
        return teamRepository.findById(id)
            .orElseThrow { IllegalArgumentException("유효하지 않은 팀입니다.") }
    }

}