package me.kvait.dto

import me.kvait.model.UserModel

class UserResponseDto(
    val id: Int,
    val email: String
) {
    companion object{
        fun fromModel(model: UserModel) = UserResponseDto(
            id = model.id,
            email = model.email
        )
    }
}