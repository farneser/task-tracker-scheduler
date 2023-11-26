package dev.farneser.tasktracker.scheduler

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile

@Profile("test")
@SpringBootTest
class TaskTrackerSchedulerApplicationTests {
    @Test
    fun contextLoads() {
        assert(true)
    }
}