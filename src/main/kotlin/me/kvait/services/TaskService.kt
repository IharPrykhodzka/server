package me.kvait.services

import io.ktor.features.*
import me.kvait.dto.TaskRequestDto
import me.kvait.dto.TaskResponseDto
import me.kvait.repository.TaskRepository

class TaskService(
    val repo: TaskRepository
) {

    suspend fun getTasks(userId: Int): List<TaskResponseDto> = repo.getAllByUserId(userId).map(TaskResponseDto.Companion::fromModel)

    suspend fun saveTask(reqTask: TaskRequestDto, userId: Int): TaskResponseDto {
        val task = repo.addTask(
            TaskRequestDto.toModel(reqTask), userId)
        return TaskResponseDto.fromModel(task)
    }

    suspend fun getByID(taskId: Int): TaskResponseDto {
        val task = repo.getById(taskId) ?: throw NotFoundException()
        return TaskResponseDto.fromModel(task)
    }

    suspend fun deleteTaskById(taskId: Int): TaskResponseDto {
        val task = repo.getById(taskId) ?: throw NotFoundException()
        val response = TaskResponseDto.fromModel(task)
        repo.deleteTask(taskId)
        return response
    }

    suspend fun updateTaskById(taskRequestDto: TaskRequestDto): TaskResponseDto {
        val task = repo.updateTask(TaskRequestDto.toModel(taskRequestDto))
        return TaskResponseDto.fromModel(task)
    }
}

