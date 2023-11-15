package com.ddn.goorm.domains.group.topic

import com.ddn.goorm.domains.BaseEntity
import com.ddn.goorm.domains.group.team.Team
import javax.persistence.*

@Entity
class Topic (
    @Column(length = 255)
    var name: String? = "",

    @Column(length = 255)
    var detail: String? = "",

    @Column(length = 255)
    val icon: String? = "",

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "team")
    var team: Team? = null
) : BaseEntity() {
}