package me.kvait.dto

import me.kvait.model.TaskModel

data class TaskResponseDto(
    val id : Int,
    val title : String,
    val content : String,
    val createdDate: String
){
    companion object{
        fun fromModel(model: TaskModel) = TaskResponseDto(
            id = model.id,
            title = model.title,
            content = model.content,
            createdDate = model.createdDate
        )
    }
}