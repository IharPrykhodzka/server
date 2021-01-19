package me.kvait.routes

import me.kvait.services.BookService
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
import me.kvait.dto.BookRequestDto

fun Route.books() {

    val bookService by di().instance<BookService>()

    get("books") {
        val allBooks = bookService.getBooks()
        call.respond(allBooks)
    }

    get("book/{id}") {
        val bookId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        call.respond(bookService.getByID(bookId))
    }

    post("book") {
        val bookRequest = call.receive<BookRequestDto>()
        bookService.saveBook(bookRequest)
        call.respond(HttpStatusCode.Accepted)
    }

    delete("book/{id}") {
        val bookId = call.parameters["id"]?.toIntOrNull() ?: throw NotFoundException()
        bookService.deleteBookById(bookId)
        call.respond(HttpStatusCode.OK)
    }
}