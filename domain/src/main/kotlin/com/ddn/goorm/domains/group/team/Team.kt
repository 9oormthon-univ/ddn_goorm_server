package com.ddn.goorm.domains.group.team

import com.ddn.goorm.domains.BaseEntity
import javax.persistence.Column

data class Team(
    @Column(length = 63)
    var name: String? = "",

    @Column(length = 255)
    var detail: String? = "",

    @Column(length = 255)
    var icon: String? = "",
) : BaseEntity() {
}