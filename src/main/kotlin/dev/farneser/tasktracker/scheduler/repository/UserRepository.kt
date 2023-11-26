package dev.farneser.tasktracker.scheduler.repository

import dev.farneser.tasktracker.scheduler.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>