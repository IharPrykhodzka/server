package me.kvait.db.data.user

import me.kvait.model.UserModel
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toUser() = UserModel(
    id = this[Users.id],
    username = this[Users.username],
    password = this[Users.password]
)