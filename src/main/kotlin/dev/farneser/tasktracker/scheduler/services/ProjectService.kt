package dev.farneser.tasktracker.scheduler.services

import dev.farneser.tasktracker.scheduler.models.Project
import dev.farneser.tasktracker.scheduler.repository.ProjectRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProjectService(private val projectRepository: ProjectRepository) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(ProjectService::class.java)
    }

    fun getByUserId(userId: Long): List<Project> {
        log.debug("Getting projects by user id: $userId started at ${System.currentTimeMillis()}")

        return projectRepository.findByUserId(userId)
    }
}