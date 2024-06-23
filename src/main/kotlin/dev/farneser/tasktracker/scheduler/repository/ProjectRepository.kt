package dev.farneser.tasktracker.scheduler.repository

import dev.farneser.tasktracker.scheduler.models.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ProjectRepository : JpaRepository<Project, Long> {
    @Query(
        value = """
            SELECT p.*
            FROM projects p
            JOIN project_members pm ON p.id = pm.project_id
            WHERE pm.member_id = :userId
        """, nativeQuery = true
    )
    fun findByUserId(@Param("userId") userId: Long): List<Project>
}