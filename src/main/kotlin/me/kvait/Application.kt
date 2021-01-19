package me.kvait

import me.kvait.di.DIBuilder
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
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

    install(Routing){
        val routingV1 by di().instance<RoutingV1>()
        routingV1.setup(this)
    }
}

