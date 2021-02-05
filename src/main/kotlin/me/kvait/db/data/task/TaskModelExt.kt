package me.kvait.db.data.task

import org.jetbrains.exposed.sql.ResultRow
import me.kvait.model.TaskModel

fun ResultRow.toTask() = TaskModel(
    id = this[Tasks.id],
    user_id = this[Tasks.user_id],
    title = this[Tasks.title],
    content = this[Tasks.content],
    createdDate = this[Tasks.createdDate]
)