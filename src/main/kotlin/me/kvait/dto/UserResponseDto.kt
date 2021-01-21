package me.kvait.dto

import me.kvait.model.UserModel

class UserResponseDto(
    val id: Int,
    val username: String
) {
    companion object{
        fun fromModel(model: UserModel) = UserResponseDto(
            id = model.id,
            username = model.username
        )
    }
}