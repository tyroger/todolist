package com.tucoderas.todolist.views;

import java.util.List;

import com.tucoderas.todolist.back.controller.TaskListController;
import com.tucoderas.todolist.back.entity.TaskEntity;
import com.tucoderas.todolist.views.styles_component.CustomizedColors;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class HomeView extends VerticalLayout {

    private TaskListController taskListController;

    private VerticalLayout headerLayout = new VerticalLayout();
    private VerticalLayout editionLayout = new VerticalLayout();
    private VerticalLayout updatingLayout = new VerticalLayout();
    private VerticalLayout taskListLayout = new VerticalLayout();
    private VerticalLayout footerLayout = new VerticalLayout();

    public HomeView(TaskListController taskListController) {
        this.taskListController = taskListController;
        setPadding(isAttached());
        add(headerLayout, editionLayout, updatingLayout, taskListLayout, footerLayout);
        initializeLayouts();

    }

    private void initializeLayouts() {
        customHeaderLayout();
        customEditionLayout();
        customFooterLayout();
        customTaskListLayout();

    }

    private void customHeaderLayout() {
        headerLayout.add(new H1("TodoListApp"));
        headerLayout.add(new Paragraph("This is a simple todo list application."));
        applyHeaderStyles();
    }

    private void customEditionLayout() {

        TextField taskField = new TextField("Edit a new task");
        Button addTaskButton = new Button("Add Task");

        editionLayout.add(taskField, addTaskButton);
        editionLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        styleTaskInputComponents(taskField, addTaskButton);


        addTaskButton.addClickListener(event -> {
            TaskEntity taskEntity = new TaskEntity();
            String taskValue = taskField.getValue();
            if (!taskValue.isEmpty() || !taskValue.isBlank()) {
                {
                    taskEntity.setTitle(taskField.getValue());
                    taskListController.addTask(taskEntity);
                    taskField.clear();
                }
            }
            customTaskListLayout();
        });
    }


    private void customTaskListLayout() {
        applyTaskListStyles();
        taskListLayout.removeAll();
        taskListLayout.setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        List<TaskEntity> tasks = taskListController.getTasks();
        tasks.forEach(task -> {
            HorizontalLayout hLayout = new HorizontalLayout();
            Paragraph taskParagraph = new Paragraph(task.getTitle());
            Button deleteButton = new Button("Delete");
            Button updateButton = new Button("Update");
            hLayout.add(taskParagraph, deleteButton, updateButton);
            taskListLayout.add(hLayout);

            taskParagraph.getStyle().set("width", "400px");
            taskParagraph.getStyle().setMaxWidth("400px");

            hLayout.getStyle().set("margin-bottom", "10px");
            hLayout.getStyle().set("padding", "5px");

            deleteButton.addClickListener(event -> {
                taskListController.deleteTask(task);
                customTaskListLayout();

            });

            updateButton.addClickListener(event -> {
                TextField taskField = new TextField("update a task");
                Button btnSubmitTask = new Button("Valid changes");
                updatingLayout.add(taskField, btnSubmitTask);
                styleUpdateComponents(taskField, btnSubmitTask);

                taskField.setValue(task.getTitle());

                btnSubmitTask.addClickListener(event2 -> {
                    updatingLayout.removeAll();
                    task.setTitle(taskField.getValue());
                    taskListController.updateTask(task);
                    customTaskListLayout();

                });
                updatingLayout.removeAll();
                updatingLayout.add(taskField, btnSubmitTask);
            });

        });
        // trier par ordre d'ajout  (le plus récent en haut)
        taskListLayout.getChildren().forEach(component -> {
            taskListLayout.getElement().insertChild(0, component.getElement());
        });

    }



    // styling methods

    private void applyHeaderStyles() {
        headerLayout.getStyle().setBackgroundColor(CustomizedColors.BACKGROUND_COLOR);
        headerLayout.getStyle().set("color", "white");
        headerLayout.getStyle().set("padding", "5px");
    }

    private void styleTaskInputComponents(TextField taskField, Button addTask) {
        //editionLayout.getStyle().setBackgroundColor("yellow");
        editionLayout.getStyle().set("border", "1px solidrgb(255, 251, 251)");
        editionLayout.getStyle().set("border-radius", "5px");
        editionLayout.getStyle().set("padding", "10px");
        taskField.getStyle().set("margin-right", "10px");
        taskField.setWidth("500px");
        addTask.getStyle().set("background-color", "#4CAF50");
        addTask.getStyle().set("color", "white");
        addTask.getStyle().set("border", "none");
        addTask.getStyle().set("border-radius", "5px");
        addTask.getStyle().set("padding", "10px 24px");
    }

    private void styleUpdateComponents(TextField taskField, Button addTask) {
        //updatingLayout.getStyle().setBackgroundColor("blue");

        updatingLayout.getStyle().set("border", "1px solidrgb(255, 255, 255)");
        updatingLayout.getStyle().set("border-radius", "5px");
        updatingLayout.getStyle().set("padding", "10px");
        taskField.getStyle().set("margin-right", "10px");
        taskField.setWidth("500px");
        addTask.getStyle().set("background-color", "#4CAF50");
        addTask.getStyle().set("color", "white");
        addTask.getStyle().set("border", "none");
        addTask.getStyle().set("border-radius", "5px");
        addTask.getStyle().set("padding", "10px 24px");
    }

    private void applyTaskListStyles() {
        taskListLayout.getStyle().set("border", "1px solidrgb(255, 255, 255)");
        taskListLayout.getStyle().set("border-radius", "5px");
        taskListLayout.getStyle().set("padding", "10px");
        // rendre le scroll possible
        taskListLayout.getStyle().set("overflow-y", "auto");
        taskListLayout.getStyle().set("height", "300px");


    }

    private void customFooterLayout() {
        // comment coller le footer en bas de la page
        footerLayout.getStyle().set("position", "fixed");
        footerLayout.getStyle().set("bottom", "0");
        footerLayout.getStyle().set("width", "100%");

        footerLayout.add(new Text("© 2024 Tucoderas"));
        footerLayout.getStyle().set("background-color", "#4CAF50");
        footerLayout.getStyle().set("color", "white");
        footerLayout.getStyle().set("padding", "10px");
    }

}
