package me.kvait.routes

import io.ktor.routing.*
import me.kvait.routes.books

class RoutingV1 {

    fun setup(configuration: Routing) {
        with(configuration) {
            route("/api/v1/") {
                books()
            }
        }
    }
}