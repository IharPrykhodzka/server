package me.kvait.repository

import me.kvait.model.TaskModel

interface TaskRepository {
    suspend fun getAllByUserId(userId: Int): List<TaskModel>
    suspend fun getById(taskId: Int): TaskModel?
    suspend fun addTask(task: TaskModel, userId: Int): TaskModel
    suspend fun deleteTask(taskId: Int)
    suspend fun updateTask(task: TaskModel): TaskModel
}