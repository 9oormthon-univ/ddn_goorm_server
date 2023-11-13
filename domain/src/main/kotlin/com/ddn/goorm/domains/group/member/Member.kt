package com.ddn.goorm.domains.group.member

import com.ddn.goorm.domains.BaseEntity
import com.ddn.goorm.domains.account.Account
import com.ddn.goorm.domains.group.team.Team
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Member(
    @Column
    var status: Status? = Status.ACTIVE,

    @Column
    var role: Role? = Role.MEMBER,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "account")
    var account: Account? = null,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "team")
    var team: Team? = null
) : BaseEntity() {
}