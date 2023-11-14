package com.ddn.goorm.admin.annotation


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class AuthAccount(val required: Boolean = true)
