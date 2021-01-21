package me.kvait.dto

import javax.print.attribute.standard.JobOriginatingUserName

class AuthenticationRequestDto(
    val username: String,
    val password: String
)