package dev.farneser.tasktracker.scheduler.services

import com.google.gson.Gson
import dev.farneser.tasktracker.scheduler.dto.ProjectDto
import dev.farneser.tasktracker.scheduler.dto.StatisticsDto
import dev.farneser.tasktracker.scheduler.dto.StatusDto
import dev.farneser.tasktracker.scheduler.services.messages.MessageService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
@EnableScheduling
class SchedulerService(
    private val messageService: MessageService,
    private val userService: UserService,
    private val statusService: StatusService,
    private val taskService: TaskService,
    private val projectService: ProjectService
) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(SchedulerService::class.java)
    }

    @Value("\${scheduler.archivedStatusEnabled:false}")
    val archivedStatusEnabled: Boolean = false

    @Scheduled(cron = "\${scheduler.cron:0 0 0 * * *}", zone = "\${scheduler.timezone:Europe/Minsk}")
    fun scheduledNotifier() {

        log.info("Scheduled task started at ${System.currentTimeMillis()}")

        val users = userService.getSubscribedUsers()

        for (user in users) {

            log.info("Getting statistics for user: ${user.email} started at ${System.currentTimeMillis()}")

            val statistics = StatisticsDto(user.email)

            val projects = projectService.getByUserId(user.id)

            log.info("Projects for user ${user.email}: $projects")

            for (project in projects) {

                val projectDto = ProjectDto(project.projectName ?: "Project name", ArrayList())

                val statuses = statusService.getByProjectId(project.id)

                log.info("Statuses in project ${project.projectName}: $statuses")

                for (status in statuses.filter { it.isCompleted == false }) {
                    val tasks = taskService.getByProjectId(project.id, status.id)

                    projectDto.statuses.add(StatusDto(status, tasks))

                    log.debug("Tasks in status ${status.statusName}: $tasks")
                }

                if (archivedStatusEnabled) {
                    log.debug("Archived status enabled")

                    val archivedTasks = taskService.getArchived(project.id)

                    projectDto.statuses.add(StatusDto("Archived", -1, archivedTasks))

                    log.debug("Archived tasks: $archivedTasks")
                }

                statistics.projects.add(projectDto)
            }

            if (statistics.projects.size != 0) {
                messageService.sendScheduledMessage(Gson().toJson(statistics))
                log.info("User {} notified successfully with statistic: {}", user.email, statistics)
            }
        }

        log.info("Scheduled task finished at ${System.currentTimeMillis()} users notified: ${users.size}")
    }
}
