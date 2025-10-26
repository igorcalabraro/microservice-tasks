package com.igorcalabraro.service.tasks;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    private final TasksRepository tasksRepository;

    public TasksController(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @PostMapping
    public ResponseEntity<TasksEntity> createTask(@RequestBody TasksRequest tasksRequest) {
        TasksEntity tasksEntity = new TasksEntity(tasksRequest);
        return ResponseEntity.ok(this.tasksRepository.save(tasksEntity));
    }
}
