package me.kvait.db.data.book

import org.jetbrains.exposed.sql.ResultRow
import me.kvait.model.BookModel

fun ResultRow.toBook() = BookModel(
    id = this[Books.id],
    title = this[Books.title],
    author = this[Books.author]
)