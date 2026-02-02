package com.taskmanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.entity.Task;
import com.taskmanagement.service.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public Task create(@RequestBody Task task) {
        return taskService.create(task);
    }

    @GetMapping
    public List<Task> list() {
        return taskService.list();
    }
}
