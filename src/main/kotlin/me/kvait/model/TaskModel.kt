package me.kvait.model

data class TaskModel(
    val id : Int,
    val user_id : Int,
    val title : String,
    val content : String,
    val createdDate : String
)