package me.kvait.routes

import me.kvait.services.TaskService
import io.ktor.application.call
import io.ktor.features.NotFoundException
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import org.kodein.di.instance
import org.kodein.di.ktor.di
import me.kvait.dto.TaskRequestDto

fun Route.tasks() {

    val taskService by di().instance<TaskService>()

    get("tasks") {
        val allTasks = taskService.getTasks()
        call.respond(allTasks)
    }

    get("task/{id}") {
        val taskId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        call.respond(taskService.getByID(taskId))
    }

    post("task") {
        val taskRequest = call.receive<TaskRequestDto>()
        taskService.saveTask(taskRequest)
        call.respond(HttpStatusCode.Accepted)
    }

    delete("task/{id}") {
        val taskId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        taskService.deleteTaskById(taskId)
        call.respond(HttpStatusCode.OK)
    }
}