package me.kvait.routes

import io.ktor.auth.*
import io.ktor.routing.*

class RoutingV1 {

    fun setup(configuration: Routing) {
        with(configuration) {
            route("/api/v1/") {
                users()

                authenticate {
                    tasks()
                }
            }
        }
    }
}