package me.kvait.db.data.book

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Books : Table() {
    val id: Column<Int> = integer("id").autoIncrement().primaryKey()
    val title: Column<String> = varchar("title", 255)
    val author: Column<String> = varchar("author", 255)
}