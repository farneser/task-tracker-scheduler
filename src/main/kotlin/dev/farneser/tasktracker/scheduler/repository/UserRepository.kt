package dev.farneser.tasktracker.scheduler.repository

import dev.farneser.tasktracker.scheduler.models.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>
}