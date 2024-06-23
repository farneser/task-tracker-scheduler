package dev.farneser.tasktracker.scheduler.config

import lombok.Getter

@Getter
class QueueType {
    companion object {
        const val GET_STATISTICS = "get_statistics"
    }
}
