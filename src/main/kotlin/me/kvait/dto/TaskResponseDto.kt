package me.kvait.dto

import me.kvait.model.TaskModel
import org.jetbrains.exposed.sql.Column

data class TaskResponseDto(
    val title : String,
    val author : String,
    val createdDate: Long
){
    companion object{
        fun fromModel(model: TaskModel) = TaskResponseDto(
            title = model.title,
            author = model.author,
            createdDate = model.createdDate
        )
    }
}