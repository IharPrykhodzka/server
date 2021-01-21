package me.kvait.repository

import io.ktor.features.*
import me.kvait.db.data.user.Users
import me.kvait.db.data.user.toUser
import me.kvait.db.dbQuery
import me.kvait.model.UserModel
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserRepositoryImpl : UserRepository {

    override suspend fun getById(id: Int): UserModel? =
        dbQuery {
            Users.select {
                (Users.id eq id)
            }.singleOrNull()?.toUser()
        }

    override suspend fun addNewUser(model: UserModel) {
        dbQuery {
            Users.insert { insertStatement ->
                insertStatement[username] = model.username
                insertStatement[password] = model.password
            }
        }
    }

    override suspend fun getByUsername(username: String): UserModel? =
        dbQuery {
            Users.select {
                (Users.username eq username)
            }.singleOrNull()?.toUser()
        }

}