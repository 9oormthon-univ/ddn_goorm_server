package com.ddn.goorm.api.post.goorm

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/goorms")
class GoormController (
    private val goormApiService: GoormApiService
) {

}