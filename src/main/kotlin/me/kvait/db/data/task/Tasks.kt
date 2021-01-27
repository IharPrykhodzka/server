package me.kvait.db.data.task

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Tasks : Table() {
    val id: Column<Int> = integer("id").autoIncrement().primaryKey()
    val title: Column<String> = varchar("title", 255)
    val author: Column<String> = varchar("author", 255)
    val createdDate: Column<Long> = long("created_date")
}