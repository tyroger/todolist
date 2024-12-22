package com.tucoderas.todolist.back.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tucoderas.todolist.back.entity.TaskEntity;
import com.tucoderas.todolist.back.repository.TaskListRepository;

@Service
public class TaskListService {

    private Long taskId = 0L;

    TaskListRepository taskListRepository;

    public TaskListService(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    public void addTask(TaskEntity task) {
        task.setId(++taskId);
        taskListRepository.addTask(task);
    }

    public List<TaskEntity> getTasks() {
        return taskListRepository.getTasks();
    }

    public void deleteTask(TaskEntity task) {
        taskListRepository.deleteTask(task.getId());
    }

    public void updateTask(TaskEntity task) {
        taskListRepository.updateTask(task);
    }

}
