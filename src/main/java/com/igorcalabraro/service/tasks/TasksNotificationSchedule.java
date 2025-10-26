package com.igorcalabraro.service.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TasksNotificationSchedule {
    private final TasksService tasksService;

    public TasksNotificationSchedule(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @Scheduled(fixedRate = 60)
    public void checkAndNotifyTasks() {
        this.tasksService.sendNotificationForDueTasks();
    }
}
