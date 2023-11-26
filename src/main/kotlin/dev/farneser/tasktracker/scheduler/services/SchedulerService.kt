package dev.farneser.tasktracker.scheduler.services

import com.google.gson.Gson
import dev.farneser.tasktracker.scheduler.dto.ColumnDto
import dev.farneser.tasktracker.scheduler.dto.StatisticDto
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

        log.info("Scheduled task started at ${System.currentTimeMillis()}")

        val users = userService.getUnsubscribedUsers()

        for (user in users) {

            log.debug("User: {}", user)

            val statistic = StatisticDto(user.email)

            val columns = columnService.getByUserId(user.id)

            for (column in columns) {
                val tasks = taskService.getByColumnId(column.id, user.id)

                statistic.columns.add(ColumnDto(column, tasks))

                log.debug("Tasks in column ${column.columnName}: $tasks")
            }

            val archivedTasks = taskService.getByColumnId(-1, user.id)

            statistic.columns.add(ColumnDto("Archived", -1, archivedTasks))

            log.debug("Archived tasks: $archivedTasks")

            messageService.sendScheduledMessage(Gson().toJson(statistic))

            log.debug("User {} notified successfully with statistic: {}", user.email, statistic)
        }

        log.info("Scheduled task finished at ${System.currentTimeMillis()}")
    }
}
