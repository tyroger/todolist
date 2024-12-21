package com.tucoderas.todolist.back.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class TaskListRepository {

    List<String>tasks = new ArrayList<>();

    public void addTask(String task) {
        tasks.add(task);
    }

    public List<String> getTasks() {
        return tasks;
    }

}