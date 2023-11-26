package dev.farneser.tasktracker.scheduler.repository

import dev.farneser.tasktracker.scheduler.models.KanbanColumn
import org.springframework.data.jpa.repository.JpaRepository

interface ColumnRepository : JpaRepository<KanbanColumn, Long> {
    fun findByUserIdOrderByOrderNumber(userId: Long): List<KanbanColumn>
}