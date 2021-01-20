package me.kvait.dto

import me.kvait.model.BookModel

data class BookRequestDto(
    val title : String,
    val author : String
) {
    companion object {
       fun toModel (dto: BookRequestDto) = BookModel(
           title = dto.title,
           author = dto.author
        )
    }
}