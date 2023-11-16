package com.ddn.goorm.api.post.goorm.dto.request

import com.ddn.goorm.domains.group.member.Member
import com.ddn.goorm.domains.group.topic.Topic
import com.ddn.goorm.domains.post.goorm.Goorm

data class GoormCreateReq(
    val title: String,
    val contents: String,
    val topic: Long
) {
    fun toEntity(topic: Topic, member: Member): Goorm {
        return Goorm(
            title = title,
            contents = contents,
            topic = topic,
            member = member
        )
    }
}