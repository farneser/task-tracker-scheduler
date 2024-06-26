package dev.farneser.tasktracker.scheduler.services

import dev.farneser.tasktracker.scheduler.models.Status
import dev.farneser.tasktracker.scheduler.repository.StatusRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class StatusService(private val statusRepository: StatusRepository) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(StatusService::class.java)
    }

    fun getByProjectId(projectId: Long): List<Status> {
        log.debug("Getting statuses by project id: $projectId started at ${System.currentTimeMillis()}")

        return statusRepository.findByProjectIdOrderByOrderNumber(projectId)
    }
}