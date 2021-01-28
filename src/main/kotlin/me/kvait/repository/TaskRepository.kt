package me.kvait.repository

import me.kvait.model.TaskModel

interface TaskRepository {
    suspend fun getAllTasks(): List<TaskModel>
    suspend fun getById(taskId: Int): TaskModel?
    suspend fun addTask(task: TaskModel): TaskModel
    suspend fun deleteTask(taskId: Int)
    suspend fun updateTask(taskId: Int, task: TaskModel): TaskModel
}