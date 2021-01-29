package com.project.client.controller;

import com.project.abstractTree.exceptions.TreeException;
import com.project.abstractTree.model.Task;
import com.project.abstractTree.model.Tree;
import com.project.client.TaskInformationDisplay;
import com.project.client.TreeTaskCreator;
import com.project.client.TreeTaskReflector;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.Pane;

import java.io.FileReader;
import java.io.IOException;

public class MainController {
    @FXML
    private Button btn;
    @FXML
    private TreeTableView<Task> treeTableView = new TreeTableView<Task>();
    @FXML
    private Pane paneProperty;
    @FXML
    private Label labelTaskName = new Label();
    @FXML
    private Label labelTaskTime = new Label();
    private Tree<Task> taskTree = TreeTaskCreator.create();

    @FXML
    private void initialize() {
        TreeTaskReflector treeTaskReflector = new TreeTaskReflector();
        treeTaskReflector.createTaskTreeTableView(taskTree, treeTableView);
        TaskInformationDisplay taskInformationDisplay = new TaskInformationDisplay(paneProperty, labelTaskName, labelTaskTime);
        treeTaskReflector.showTreeInTreeTableView(taskTree, treeTableView);
    }

    @FXML
    public void initTable() {
        Tree<Task> taskTree2 = new Tree<Task>();
        try {
            taskTree2.read(new FileReader("treeFile.json"));
        } catch (IOException e) {

        } catch (TreeException e) {

        }
        TreeItem<Task> root = new TreeItem<Task>(taskTree2.getRoot().getValue());
        treeTableView.setRoot(root);
        TreeItem<Task> item = new TreeItem<Task>(taskTree2.search(1).getValue());
        root.getChildren().add(item);
        //treeColumnTask.setCellValueFactory(new TreeItemPropertyValueFactory<Task, String>("name"));
    }
}
