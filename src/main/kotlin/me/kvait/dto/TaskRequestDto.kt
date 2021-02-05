package me.kvait.dto

import me.kvait.model.TaskModel

data class TaskRequestDto(
    val id : Int,
    val user_id : Int,
    val title : String,
    val content : String,
    val createdDate : String
) {
    companion object {
       fun toModel (dto: TaskRequestDto) = TaskModel(
           id = dto.id,
           user_id = dto.user_id,
           title = dto.title,
           content = dto.content,
           createdDate = dto.createdDate
        )
    }
}