package com.project.client.controller;

import com.project.abstractTree.exceptions.TreeException;
import com.project.abstractTree.model.Task;
import com.project.abstractTree.model.Tree;
import com.project.client.TreeTaskCreator;
import com.project.client.TreeTaskReflector;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.FileReader;
import java.io.IOException;

public class MainController {
    @FXML
    private Label labelTaskName = new Label();
    @FXML
    private Button btn;
    @FXML
    private final Pane pane = new Pane();
    @FXML
    private TreeTableView<Task> treeTableView = new TreeTableView<Task>();
    @FXML
    private TreeTableColumn<Task, String> treeColumnTask;
    @FXML
    private TreeTableColumn<Task, Boolean> treeColumnActive;
    @FXML
    private TreeTableColumn<Task, String> treeColumnActiveTime;
    private Tree<Task> taskTree = TreeTaskCreator.create();
    Task emp;

    public MainController() {

    }

    @FXML
    private void initialize() {
        TreeTaskReflector treeTaskReflector = new TreeTaskReflector();
        treeTaskReflector.createTaskTreeTableView(taskTree, treeTableView);
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
        treeColumnTask.setCellValueFactory(new TreeItemPropertyValueFactory<Task, String>("name"));
    }
}
