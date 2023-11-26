package dev.farneser.tasktracker.scheduler.repository

import dev.farneser.tasktracker.scheduler.models.KanbanColumn
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ColumnRepository : JpaRepository<KanbanColumn, Long> {
    fun findByUserIdOrderByOrderNumber(userId: Long): List<KanbanColumn>

    @Query(value = "SELECT COUNT(*) FROM tasks WHERE column_id = :columnId AND user_id = :userId", nativeQuery = true)
    fun getTaskCountByColumnAndUser(@Param("columnId") columnId: Long, @Param("userId") userId: Long): Long
}