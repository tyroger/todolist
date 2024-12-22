package com.tucoderas.todolist.back.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;

import com.tucoderas.todolist.back.entity.TaskEntity;
import com.tucoderas.todolist.back.service.TaskListService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class TaskListController {

    TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    public List<TaskEntity> getTasks() {
        return taskListService.getTasks();
    }

    public void addTask(@RequestBody TaskEntity task) {
        taskListService.addTask(task);
    }

    public void deleteTask(@RequestBody TaskEntity task) {
        taskListService.deleteTask(task);
    }

    public void updateTask(@RequestBody TaskEntity task) {
        taskListService.updateTask(task);
    }

}
