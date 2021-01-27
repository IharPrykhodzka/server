package me.kvait.db.data.task

import org.jetbrains.exposed.sql.ResultRow
import me.kvait.model.TaskModel

fun ResultRow.toTask() = TaskModel(
    title = this[Tasks.title],
    author = this[Tasks.author],
    createdDate = this[Tasks.createdDate]
)