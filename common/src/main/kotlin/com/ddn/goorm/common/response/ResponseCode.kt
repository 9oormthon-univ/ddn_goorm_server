package com.ddn.goorm.common.response

enum class ResponseCode(
    val code: Int,
    val status: String
) {
    OK(200, "OK"),
    CREATED(201, "CREATED"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    UNAUTHORIZED(401, "UNAUTHORIZED"),
    NOT_FOUND(404, "NOT_FOUND"),
    METHOD_NOT_ALLOWED(405, "METHOD_NOT_ALLOWED"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR");
}