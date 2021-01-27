package me.kvait.dto

import me.kvait.model.TaskModel

data class TaskRequestDto(
    val title : String,
    val author : String,
    val createdDate : Long
) {
    companion object {
       fun toModel (dto: TaskRequestDto) = TaskModel(
           title = dto.title,
           author = dto.author,
           createdDate = dto.createdDate
        )
    }
}