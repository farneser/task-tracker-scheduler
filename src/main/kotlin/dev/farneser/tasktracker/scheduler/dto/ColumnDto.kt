package dev.farneser.tasktracker.scheduler.dto

import dev.farneser.tasktracker.scheduler.models.KanbanColumn
import kotlinx.serialization.Serializable

@Serializable
data class ColumnDto(val columnName: String, val orderNumber: Long, val tasks: Long) {
    constructor(column: KanbanColumn, tasks: Long) : this(
        column.columnName ?: "",
        column.orderNumber ?: 0,
        tasks
    )
}