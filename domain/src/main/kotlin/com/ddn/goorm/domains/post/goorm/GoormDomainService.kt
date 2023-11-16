package com.ddn.goorm.domains.post.goorm

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class GoormDomainService (
    private val goormRepository: GoormRepository
) {
    fun createGoorm(goorm: Goorm): Goorm {
        return goormRepository.save(goorm)
    }

    @Transactional(readOnly = true)
    fun findAllByTopicId(topic: Long): List<Goorm>   {
        return goormRepository.findAllByTopic_Id(topic)
    }

    @Transactional(readOnly = true)
    fun findById(id: Long): Goorm {
        return goormRepository.findById(id)
            .orElseThrow { IllegalArgumentException("구름이 존재하지 않습니다.") }
    }

    fun updateGoormFinStatus(goorm: Goorm): Goorm {
        goorm.updateIsFin()
        return goormRepository.save(goorm)
    }
}