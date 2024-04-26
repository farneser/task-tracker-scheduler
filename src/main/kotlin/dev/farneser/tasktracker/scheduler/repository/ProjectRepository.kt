package dev.farneser.tasktracker.scheduler.repository

import dev.farneser.tasktracker.scheduler.models.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ProjectRepository : JpaRepository<ProjectRepository, Long> {
    @Query(
        value = """
            SELECT projects.id, projects.project_name
            FROM projects
            JOIN project_members ON projects.id = project_members.project_id
            WHERE project_members.member_id = :user_id;
        """, nativeQuery = true
    )
    fun findByUserId(@Param("user_id") userId: Long): List<Project>
}