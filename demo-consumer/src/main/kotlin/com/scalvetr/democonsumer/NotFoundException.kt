package com.scalvetr.democonsumer

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found")  // 404
class NotFoundException : RuntimeException() {
}