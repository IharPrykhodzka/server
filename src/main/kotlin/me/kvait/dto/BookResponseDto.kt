package me.kvait.dto

import me.kvait.model.BookModel

data class BookResponseDto(
    val title : String,
    val author : String
){
    companion object{
        fun fromModel(model: BookModel) = BookResponseDto(
            title = model.title,
            author = model.author
        )
    }
}