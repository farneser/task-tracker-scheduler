package dev.farneser.tasktracker.scheduler.repository

import dev.farneser.tasktracker.scheduler.models.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface StatusRepository : JpaRepository<Status, Long> {

    @Query(
        value = "SELECT * FROM statuses where project_id = :project_id ORDER BY order_number",
        nativeQuery = true
    )
    fun findByProjectIdOrderByOrderNumber(@Param("project_id") projectId: Long): List<Status>

    @Query(
        value = "SELECT COUNT(*) FROM tasks where project_id = :project_id AND status_id = :status_id",
        nativeQuery = true
    )
    fun getTasksCountByProjectIdAndStatusId(
        @Param("project_id") projectId: Long,
        @Param("status_id") statusId: Long
    ): Long

    @Query(
        value = "SELECT COUNT(*) FROM tasks where project_id = :project_id AND status_id < 1 OR status_id == null ",
        nativeQuery = true
    )
    fun getArchivedTasksCountByProjectId(@Param("project_id") projectId: Long): Long
}