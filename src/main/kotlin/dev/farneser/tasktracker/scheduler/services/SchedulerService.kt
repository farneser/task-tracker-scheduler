package dev.farneser.tasktracker.scheduler.services

import dev.farneser.tasktracker.scheduler.services.messages.MessageService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@EnableScheduling
class SchedulerService(
    private val messageService: MessageService,
    private val userService: UserService,
    private val columnService: ColumnService,
    private val taskService: TaskService
) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(SchedulerService::class.java)
    }

    @Scheduled(cron = "\${scheduler.cron:0 0 0 * * *}")
    fun scheduledNotifier() {

        log.info("Scheduled task executed at ${System.currentTimeMillis()}")

        val users = userService.getUnsubscribedUsers()

        for (user in users) {

            val columns = columnService.getByUserId(user.id)

            for (column in columns) {
                val tasks = taskService.getByColumnId(column.id, user.id)

                log.info("Tasks: $tasks")
            }

            val archivedTasks = taskService.getByColumnId(-1, user.id)

            log.info("Archived tasks: $archivedTasks")

            messageService.sendScheduledMessage("")
        }
    }
}
