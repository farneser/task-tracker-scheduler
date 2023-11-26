package dev.farneser.tasktracker.scheduler.services

import dev.farneser.tasktracker.scheduler.repository.ColumnRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: ColumnRepository) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(TaskService::class.java)
    }

    fun getByColumnId(columnId: Long, userId: Long): Long {
        log.debug("Getting tasks by column id: $columnId started at ${System.currentTimeMillis()}")

        return taskRepository.getTaskCountByColumnAndUser(columnId, userId)
    }
}