package com.ddn.goorm.admin.dto

data class TokenDto (
    val accessToken: String,
    val refreshToken: String
) {
}