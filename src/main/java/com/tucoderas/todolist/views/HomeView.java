package com.tucoderas.todolist.views;

import java.util.List;

import com.tucoderas.todolist.back.controller.TaskListController;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class HomeView extends VerticalLayout {

    private TaskListController taskListController;
    private VerticalLayout taskLayout = new VerticalLayout();


    Text appName = new Text("Todo List Application");
    Text appDescription = new Text("This is a simple todo list application");

    TextField taskField = new TextField("Edit a new task");
    Button addTask = new Button("Add Task");

    public HomeView(TaskListController taskListController) {
        this.taskListController = taskListController;

        add(new H1(appName));
        add(new Paragraph(appDescription));

        add(taskField, addTask);
        add(taskLayout);

        addTask.addClickListener(e -> {
            String newTask = taskField.getValue();
            if (newTask != null && !newTask.isEmpty()) {
                taskListController.addTask(newTask);
            }
            refreshTasks();
            taskField.clear();
        });

    }

    private void refreshTasks() {
        taskLayout.removeAll();
        List<String> tasks = taskListController.getTasks();
        tasks.forEach(task -> 
            taskLayout.add(new Paragraph(task))
        );

    }

}
