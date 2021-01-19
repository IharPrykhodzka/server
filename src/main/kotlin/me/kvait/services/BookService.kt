package me.kvait.services

import io.ktor.features.*
import me.kvait.dto.BookRequestDto
import me.kvait.dto.BookResponseDto
import me.kvait.repository.BookRepository

class BookService(
    val repo: BookRepository
) {

    suspend fun getBooks(): List<BookResponseDto> = repo.getAllBooks().map(BookResponseDto.Companion::fromModel)

    suspend fun saveBook(reqBook: BookRequestDto): BookResponseDto {
        val book = repo.addBook(
            BookRequestDto.toModel(reqBook)
        )
        return BookResponseDto.fromModel(book)
    }

    suspend fun getByID(bookId: Int): BookResponseDto {
        val book = repo.getById(bookId) ?: throw NotFoundException()
        return BookResponseDto.fromModel(book)
    }

    suspend fun deleteBookById(bookId: Int): BookResponseDto {
        val book = repo.getById(bookId) ?: throw NotFoundException()
        val response = BookResponseDto.fromModel(book)
        repo.deleteBook(bookId)
        return response
    }
}

