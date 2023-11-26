package dev.farneser.tasktracker.scheduler.services

import dev.farneser.tasktracker.scheduler.models.KanbanColumn
import dev.farneser.tasktracker.scheduler.repository.ColumnRepository
import org.springframework.stereotype.Service

@Service
class ColumnService(private val columnRepository: ColumnRepository) {
    fun getByUserId(userId: Long): List<KanbanColumn> {
        return columnRepository.findByUserIdOrderByOrderNumber(userId)
    }
}