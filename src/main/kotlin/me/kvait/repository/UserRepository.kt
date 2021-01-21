package me.kvait.repository

import me.kvait.model.UserModel

interface UserRepository {

    suspend fun getById(id: Int): UserModel?
    suspend fun addNewUser(model: UserModel)
    suspend fun getByUsername(username: String): UserModel?
}