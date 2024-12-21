package com.tucoderas.todolist.back.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tucoderas.todolist.back.repository.TaskListRepository;

@Service
public class TaskListService {

    TaskListRepository taskListRepository;

    public TaskListService(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    public void addTask(String task) {
        taskListRepository.addTask(task);
    }

    public List<String> getTasks() {
        return taskListRepository.getTasks();
    }

}
