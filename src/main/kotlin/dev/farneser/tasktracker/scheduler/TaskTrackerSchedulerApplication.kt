package dev.farneser.tasktracker.scheduler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaskTrackerSchedulerApplication

fun main(args: Array<String>) {
    runApplication<TaskTrackerSchedulerApplication>(*args)
}
