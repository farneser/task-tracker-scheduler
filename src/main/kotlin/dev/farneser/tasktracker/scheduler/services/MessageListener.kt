package dev.farneser.tasktracker.scheduler.services

import com.google.gson.Gson
import dev.farneser.tasktracker.scheduler.config.QueueType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component


@Component
@EnableRabbit
class MessageListener(
    private val schedulerService: SchedulerService,
    private val userService: UserService,
) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(MessageListener::class.java)
    }

    @RabbitListener(queues = [QueueType.GET_STATISTICS])
    fun receiveMessageFromRegisterQueue(message: String) {
        log.info("Received message from register queue at ${System.currentTimeMillis()} : $message")

        val id = Gson().fromJson(message, Long::class.java)

        val user = userService.getUser(id);

        if (user != null) {
            schedulerService.notifyUser(user)
        }
    }
}
