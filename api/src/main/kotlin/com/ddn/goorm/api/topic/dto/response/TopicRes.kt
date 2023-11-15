package com.ddn.goorm.api.topic.dto.response

import com.ddn.goorm.domains.group.topic.Topic

class TopicRes (
    topic: Topic
) {
    val id = topic.id
    val name = topic.name
    val detail = topic.detail
    val status = topic.status
}