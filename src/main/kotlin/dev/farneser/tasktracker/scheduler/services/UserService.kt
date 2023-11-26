package dev.farneser.tasktracker.scheduler.services

import dev.farneser.tasktracker.scheduler.models.User
import dev.farneser.tasktracker.scheduler.repository.UserRepository
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun getUnsubscribedUsers(): List<User> {
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "isSubscribed"))
    }
}