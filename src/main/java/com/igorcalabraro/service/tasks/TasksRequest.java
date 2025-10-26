package com.igorcalabraro.service.tasks;

import java.time.LocalDateTime;

public record TasksRequest(String title, String email, LocalDateTime dueDate, boolean notified) {

}
