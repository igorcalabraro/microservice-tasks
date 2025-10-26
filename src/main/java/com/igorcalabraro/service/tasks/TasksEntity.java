package com.igorcalabraro.service.tasks;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "Task")
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TasksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String email;
    private LocalDateTime dueDate;
    private boolean notified;

    public TasksEntity(TasksRequest tasksRequest) {
        this.title = tasksRequest.title();
        this.email = tasksRequest.email();
        this.dueDate = tasksRequest.dueDate();
        this.notified = tasksRequest.notified();
    }
}
