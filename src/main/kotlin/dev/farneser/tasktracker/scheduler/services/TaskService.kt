package dev.farneser.tasktracker.scheduler.services

import dev.farneser.tasktracker.scheduler.repository.StatusRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: StatusRepository) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(TaskService::class.java)
    }

    fun getByProjectId(projectId: Long, statusId: Long): Long {
        log.debug("Getting tasks by project id: $projectId started at ${System.currentTimeMillis()}")

        return taskRepository.getTasksCountByProjectIdAndStatusId(projectId, statusId)
    }

    fun getArchived(projectId: Long): Long {
        log.debug("Getting archived tasks by project id $projectId at ${System.currentTimeMillis()}")

        return taskRepository.getArchivedTasksCountByProjectId(projectId)
    }
}