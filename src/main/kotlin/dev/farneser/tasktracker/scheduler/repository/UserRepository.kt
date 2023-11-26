package dev.farneser.tasktracker.scheduler.repository

import dev.farneser.tasktracker.scheduler.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun fundByNotIsSubscribed(): List<User>
}