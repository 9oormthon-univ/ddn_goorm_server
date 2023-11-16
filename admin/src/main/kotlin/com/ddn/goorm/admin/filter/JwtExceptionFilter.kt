package com.ddn.goorm.admin.filter

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.JwtException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtExceptionFilter: OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: JwtException) {
            throwException(request, response, e)
        }
    }

    @Throws(IOException::class)
    private fun throwException(
        request: HttpServletRequest,
        response: HttpServletResponse,
        e: Exception
    ) {
        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE

        val mapper = ObjectMapper()
        val body: MutableMap<String, Any> = HashMap()
        body["status"] = HttpServletResponse.SC_UNAUTHORIZED
        body["error"] = HttpStatus.UNAUTHORIZED.name
        body["trace"] = e.localizedMessage
        body["message"] = e.message!!
        body["path"] = request.servletPath

        mapper.writeValue(response.outputStream, body)
        response.status = HttpServletResponse.SC_OK
    }
}