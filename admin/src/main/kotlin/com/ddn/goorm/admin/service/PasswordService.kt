package com.ddn.goorm.admin.service

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class PasswordService (
    val passwordEncoder: PasswordEncoder
) {
    fun encryptPassword(password: String) : String {
        return passwordEncoder.encode(password)
    }

    fun isValidPassword(inputPassword: String, encryptPassword: String) : Boolean {
        return passwordEncoder.matches(inputPassword, encryptPassword);
    }
}