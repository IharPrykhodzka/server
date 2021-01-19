package me.kvait.repository

import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import me.kvait.db.data.book.Books
import me.kvait.db.data.book.toBook
import me.kvait.db.dbQuery
import me.kvait.model.BookModel

class BookRepositoryImpl : BookRepository {

    override suspend fun getAllBooks(): List<BookModel> =
        dbQuery {
            Books.selectAll().toList().map {
                it.toBook()
            }
        }


    override suspend fun getById(bookId: Int): BookModel? =
        dbQuery {
            Books.select {
                (Books.id eq bookId)
            }.singleOrNull()?.toBook()

        }


    override suspend fun addBook(book: BookModel): BookModel =
        dbQuery {
            val bookId: Int = Books.insert { insertStatement ->
                insertStatement[id] = book.id
                insertStatement[title] = book.title
                insertStatement[author] = book.author
            }[Books.id]

            Books.select {
                Books.id eq bookId
            }.single().toBook()
        }


    override suspend fun deleteBook(bookId: Int) {
        dbQuery {
            Books.deleteWhere { Books.id eq bookId }
        }
    }
}