package me.kvait.routes

import io.ktor.application.*
import me.kvait.services.TaskService
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.util.pipeline.*
import org.kodein.di.instance
import org.kodein.di.ktor.di
import me.kvait.dto.TaskRequestDto
import me.kvait.model.UserModel

val <T: Any> PipelineContext<T, ApplicationCall>.id
    get() = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()

val <T: Any> PipelineContext<T, ApplicationCall>.me
    get() = call.authentication.principal<UserModel>()

fun Route.tasks() {

    val taskService by di().instance<TaskService>()

    get("tasks") {
        val allTasks = taskService.getTasks()
        call.respond(allTasks)
    }

    get("task/{id}") {
        call.respond(taskService.getByID(id))
    }

    post("task/save") {
        val taskRequest = call.receive<TaskRequestDto>()
        taskService.saveTask(taskRequest)
        call.respond(HttpStatusCode.Accepted)
    }

    delete("task/{id}") {
        taskService.deleteTaskById(id)
        call.respond(HttpStatusCode.OK)
    }

    post("task/{id}/update") {
        val taskRequest = call.receive<TaskRequestDto>()
        taskService.updateTaskById(id, taskRequest)
        call.respond(HttpStatusCode.Accepted)
    }
}