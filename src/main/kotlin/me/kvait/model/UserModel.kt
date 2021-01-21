package me.kvait.model

import io.ktor.auth.*

class UserModel(
    val id: Int = 0,
    val username: String,
    val password: String
): Principal