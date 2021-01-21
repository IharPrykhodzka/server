package me.kvait

import me.kvait.di.DIBuilder
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.server.netty.*
import io.ktor.util.KtorExperimentalAPI
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import org.kodein.di.instance
import org.kodein.di.ktor.di
import me.kvait.routes.RoutingV1
import me.kvait.services.JWTTokenService
import me.kvait.services.UserService
import org.kodein.di.ktor.kodein

fun main(args: Array<String>) {
    EngineMain.main(args)
}


@KtorExperimentalAPI
fun Application.module() {

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            serializeNulls()
        }}
    install(CallLogging)
    install(StatusPages) {
        exception<EntityNotFoundException> {
            call.respond(HttpStatusCode.NotFound)
        }
    }

    di {
        DIBuilder(environment).setup(this)
    }

    install(Authentication) {
        jwt {
            val jwtService by di().instance<JWTTokenService>()
            verifier(jwtService.verifier)

            val userService by di().instance<UserService>()
            validate {
                val id = it.payload.getClaim("id").asInt()
                userService.getModelById(id)
            }
        }
    }

    install(Routing){
        val routingV1 by di().instance<RoutingV1>()
        routingV1.setup(this)
    }
}

