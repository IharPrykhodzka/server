package me.kvait.db.data.user

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id: Column<Int> = integer("id").autoIncrement().primaryKey()
    val email: Column<String> = varchar("email", 100)
    val password: Column<String> = varchar("password", 100)
}