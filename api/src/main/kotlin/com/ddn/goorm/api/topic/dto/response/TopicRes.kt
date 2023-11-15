package com.ddn.goorm.api.topic.dto.response

import com.ddn.goorm.domains.group.topic.Topic

class TopicRes (
    topic: Topic
) {
    val name = topic.name
    val icon = topic.icon
    val detail = topic.detail
}