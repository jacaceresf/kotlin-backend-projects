package dev.jacaceresf.kotlinmongo.kotlinmongopractice.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(RuntimeException::class)])
    fun handleRuntimeException(
        ex: java.lang.RuntimeException,
        request: WebRequest
    ): ResponseEntity<Map<String, String>> {
        return ResponseEntity.badRequest().body(
            mapOf("code" to HttpStatus.BAD_REQUEST.toString(), "error" to ex.message!!)
        )
    }
}