package com.ddn.goorm.domains.post.comment

import com.ddn.goorm.domains.BaseEntity
import com.ddn.goorm.domains.group.member.Member
import com.ddn.goorm.domains.post.goorm.Goorm
import javax.persistence.*

@Entity
class Comment(
    @Column(columnDefinition = "text")
    var contents: String? = "",

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "member")
    var member: Member? = null,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "goorm")
    var goorm: Goorm? = null,
) : BaseEntity() {
}