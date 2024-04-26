package dev.farneser.tasktracker.scheduler.dto

import dev.farneser.tasktracker.scheduler.models.Status

data class StatusDto(val statusName: String, val orderNumber: Long, val tasks: Long) {
    constructor(status: Status, tasks: Long) : this(
        status.statusName ?: "",
        status.orderNumber ?: 0,
        tasks
    )
}