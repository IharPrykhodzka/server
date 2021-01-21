package me.kvait.routes

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import me.kvait.dto.AuthenticationRequestDto
import me.kvait.dto.BookRequestDto
import me.kvait.dto.UserRegisterRequestDto
import me.kvait.services.BookService
import me.kvait.services.UserService
import org.kodein.di.instance
import org.kodein.di.ktor.di

fun Route.users() {

    val userService by di().instance<UserService>()

    post("registration") {
        val input = call.receive<UserRegisterRequestDto>()
        val response = userService.register(input)
        call.respond(response)
    }

    post("authentication") {
        val input = call.receive<AuthenticationRequestDto>()
        val response = userService.authenticate(input)
        call.respond(response)
    }
}