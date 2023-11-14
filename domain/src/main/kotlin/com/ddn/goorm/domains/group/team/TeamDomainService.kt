package com.ddn.goorm.domains.group.team

import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class TeamDomainService (
    private val teamRepository: TeamRepository
) {

}