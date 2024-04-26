package dev.farneser.tasktracker.scheduler.dto

import jakarta.validation.constraints.Email

data class StatisticsDto(@Email val email: String) {
    val projects: ArrayList<ProjectDto> = ArrayList()
}
