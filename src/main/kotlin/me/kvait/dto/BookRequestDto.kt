package me.kvait.dto

import me.kvait.model.BookModel

data class BookRequestDto(
    val id : Int,
    val title : String,
    val author : String
) {
    companion object {
       fun toModel (dto: BookRequestDto) = BookModel(
           id = dto.id,
           title = dto.title,
           author = dto.author
        )
    }
}