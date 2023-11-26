package dev.farneser.tasktracker.scheduler.services

import dev.farneser.tasktracker.scheduler.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: TaskRepository) {
    fun getByColumnId(columnId: Long, userId: Long): Long {
        return taskRepository.getTaskCountByColumnAndUser(columnId, userId)
    }
}