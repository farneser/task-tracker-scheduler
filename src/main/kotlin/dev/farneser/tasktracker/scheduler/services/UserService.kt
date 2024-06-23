package dev.farneser.tasktracker.scheduler.services

import dev.farneser.tasktracker.scheduler.models.User
import dev.farneser.tasktracker.scheduler.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(UserService::class.java)
    }

    fun getSubscribedUsers(): List<User> {
        log.debug("Getting unsubscribed users started at ${System.currentTimeMillis()}")

        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "isSubscribed"))
            .filter {
                it.isSubscribed != null
                        && it.isSubscribed == true
                        && it.isEnabled != null
                        && it.isEnabled == true
                        && it.isLocked == false
            }
    }

    fun getUser(id: Long): User? {
        log.debug("Getting user by id $id")

        return userRepository.findById(id).get()
    }
}