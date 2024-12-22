package com.tucoderas.todolist.back.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tucoderas.todolist.back.entity.TaskEntity;

@Repository
public class TaskListRepository {

    List<TaskEntity>tasks = new ArrayList<>();

    public void addTask(TaskEntity task) {
        tasks.add(task);
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void deleteTask(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

    public void updateTask(TaskEntity task) {
        tasks.removeIf(t -> t.getId().equals(task.getId()));
        tasks.add(task);
    }

}