package com.tucoderas.todolist.back.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
public class TaskEntity {

    private Long id;
    private String title;
    private String status = "PENDING";

}
