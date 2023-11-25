package dev.farneser.tasktracker.scheduler.services.messages

import lombok.Getter

@Getter
enum class QueueType(private val queueName: String) {
    SCHEDULED("scheduled");

    override fun toString(): String {
        return queueName
    }
}