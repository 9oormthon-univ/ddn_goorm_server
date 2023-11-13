package com.ddn.goorm.domains.account

import com.ddn.goorm.domains.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity

@Entity
data class Account(
    @Column(length = 63)
    var email: String? = "",

    @Column(length = 255)
    var password: String? = "",

    @Column(length = 63)
    var nickname: String? = "",

    @Column
    var isDeleted: Boolean? = false,

    @Column
    var isCertified: Boolean? = false,
) : BaseEntity() {
}