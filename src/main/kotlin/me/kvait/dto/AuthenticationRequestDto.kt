package me.kvait.dto

import javax.print.attribute.standard.JobOriginatingUserName

class AuthenticationRequestDto(
    val email: String,
    val password: String
)