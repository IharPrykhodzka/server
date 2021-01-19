package me.kvait.dto

import me.kvait.model.BookModel

data class BookResponseDto(
    val id : Int,
    val title : String,
    val author : String
){
    companion object{
        fun fromModel(model: BookModel) = BookResponseDto(
            id = model.id,
            title = model.title,
            author = model.author
        )
    }
}