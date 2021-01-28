package me.kvait.repository

import me.kvait.db.data.task.Tasks
import me.kvait.db.data.task.toTask
import me.kvait.db.dbQuery
import me.kvait.model.TaskModel
import org.jetbrains.exposed.sql.*

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
                insertStatement[content] = task.content
                insertStatement[createdDate] = task.createdDate
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

    override suspend fun updateTask(taskId: Int, task: TaskModel): TaskModel =
        dbQuery {
            Tasks.update { updateStatement ->
                updateStatement[title] = task.title
                updateStatement[content] = task.content
                updateStatement[createdDate] = task.createdDate
            }

            Tasks.select {
                Tasks.id eq taskId
            }.single().toTask()
        }
}

