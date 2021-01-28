package me.kvait.dto

import me.kvait.model.TaskModel

data class TaskRequestDto(
    val id : Int,
    val title : String,
    val content : String,
    val createdDate : String
) {
    companion object {
       fun toModel (dto: TaskRequestDto) = TaskModel(
           id = dto.id,
           title = dto.title,
           content = dto.content,
           createdDate = dto.createdDate
        )
    }
}