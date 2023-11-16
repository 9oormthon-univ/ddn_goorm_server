package com.ddn.goorm.domains.group.team

import com.ddn.goorm.common.enums.Status
import com.ddn.goorm.domains.BaseEntity
import com.ddn.goorm.domains.group.topic.Topic
import com.ddn.goorm.domains.post.comment.Comment
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
data class Team(
    @Column(length = 63)
    var name: String? = "",

    @Column(length = 255)
    var detail: String? = "",

    @Column(length = 255)
    var icon: String? = "",

    @Column
    var status: Status? = Status.ACTIVE,
) : BaseEntity() {
    @OneToMany(
        mappedBy = "team",
        cascade = [CascadeType.ALL]
    )
    var topics: List<Topic>? = null
}