package me.kvait.repository

import me.kvait.model.BookModel

interface BookRepository {
    suspend fun getAllBooks(): List<BookModel>
    suspend fun getById(bookId: Int): BookModel?
    suspend fun addBook(book: BookModel): BookModel
    suspend fun deleteBook(bookId: Int)

}