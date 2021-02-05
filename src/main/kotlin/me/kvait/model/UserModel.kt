package me.kvait.model

import io.ktor.auth.*

class UserModel(
    val id: Int = 0,
    val email: String,
    val password: String
): Principal