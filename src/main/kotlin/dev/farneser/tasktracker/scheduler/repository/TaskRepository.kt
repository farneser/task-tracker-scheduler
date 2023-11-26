package dev.farneser.tasktracker.scheduler.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.Repository
import org.springframework.data.repository.query.Param

interface TaskRepository : Repository<Any, Long> {

    @Query(value = "SELECT COUNT(*) FROM tasks WHERE column_id = :columnId AND user_id = :userId", nativeQuery = true)
    fun getTaskCountByColumnAndUser(@Param("columnId") columnId: Long, @Param("userId") userId: Long): Long
}