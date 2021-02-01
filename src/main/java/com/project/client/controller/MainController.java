package com.project.client.controller;

import com.project.abstractTree.model.Task;
import com.project.abstractTree.model.Tree;
import com.project.client.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class MainController {
    @FXML
    private Button btnEditTask = new Button();
    @FXML
    private Button btnAddTask;
    @FXML
    private Button btnDeleteTask;
    @FXML
    private TreeTableView<Task> treeTableView = new TreeTableView<Task>();
    @FXML
    private Pane paneProperty;
    @FXML
    private Label labelTaskName = new Label();
    @FXML
    private Label labelTaskTime = new Label();
    @FXML
    private Pane paneEdit = new Pane();
    @FXML
    private TextField textFieldEditName;
    @FXML
    private Button btnSubmitEdit;
    @FXML
    private Pane paneAdd = new Pane();
    @FXML
    private Button btnSubmitAdd;
    @FXML
    private TextField textFieldAddTaskName;
    @FXML
    private Pane paneDelete;
    @FXML
    private ChoiceBox<String> choiceBoxDeleteType;
    @FXML
    private Button btnSubmitDelete;

    private Tree<Task> taskTree = TreeTaskCreator.create();

    @FXML
    private void initialize() {
        TreeTaskReflector treeTaskReflector = new TreeTaskReflector();
        TreeTaskEdit treeTaskEdit = new TreeTaskEdit(treeTableView, btnEditTask, paneEdit, textFieldEditName, btnSubmitEdit);
        TreeTaskAdd treeTaskAdd = new TreeTaskAdd(treeTableView, btnAddTask, paneAdd, textFieldAddTaskName, btnSubmitAdd);
        TreeTaskDelete treeTaskDelete = new TreeTaskDelete(treeTableView, btnDeleteTask, paneDelete, choiceBoxDeleteType, btnSubmitDelete);
        TaskInformationDisplay taskInformationDisplay = new TaskInformationDisplay(paneProperty, labelTaskName, labelTaskTime);
        treeTaskReflector.createTaskTreeTableView(taskTree, treeTableView);
        treeTaskReflector.showTreeInTreeTableView(taskTree, treeTableView, taskInformationDisplay);
    }
}
