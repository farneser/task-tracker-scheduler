package dev.farneser.tasktracker.scheduler.services

import dev.farneser.tasktracker.scheduler.models.KanbanColumn
import dev.farneser.tasktracker.scheduler.repository.ColumnRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ColumnService(private val columnRepository: ColumnRepository) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(ColumnService::class.java)
    }

    fun getByUserId(userId: Long): List<KanbanColumn> {
        log.debug("Getting columns by user id: $userId started at ${System.currentTimeMillis()}")

        return columnRepository.findByUserIdOrderByOrderNumber(userId)
    }
}