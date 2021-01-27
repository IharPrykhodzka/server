package me.kvait.repository

import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import me.kvait.db.data.task.Tasks
import me.kvait.db.data.task.toTask
import me.kvait.db.dbQuery
import me.kvait.model.TaskModel

class TaskRepositoryImpl : TaskRepository {

    override suspend fun getAllTasks(): List<TaskModel> =
        dbQuery {
            Tasks.selectAll().toList().map {
                it.toTask()
            }
        }


    override suspend fun getById(taskId: Int): TaskModel? =
        dbQuery {
            Tasks.select {
                (Tasks.id eq taskId)
            }.singleOrNull()?.toTask()

        }


    override suspend fun addTask(task: TaskModel): TaskModel =
        dbQuery {
            val taskId: Int = Tasks.insert { insertStatement ->
                insertStatement[title] = task.title
                insertStatement[author] = task.author
            }[Tasks.id]

            Tasks.select {
                Tasks.id eq taskId
            }.single().toTask()
        }


    override suspend fun deleteTask(taskId: Int) {
        dbQuery {
            Tasks.deleteWhere { Tasks.id eq taskId }
        }
    }
}