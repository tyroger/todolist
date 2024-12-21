package com.tucoderas.todolist.back.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tucoderas.todolist.back.service.TaskListService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class TaskListController {

    TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @GetMapping()
    public List<String> getTasks() {
        return taskListService.getTasks();
    }

    @PostMapping()
    public void addTask(@RequestBody String task) {
        taskListService.addTask(task);
    }

}
