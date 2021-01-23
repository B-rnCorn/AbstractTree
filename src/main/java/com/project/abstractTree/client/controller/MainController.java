package com.project.abstractTree.client.controller;

import com.project.abstractTree.model.Node;
import com.project.abstractTree.model.Tree;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.Pane;

public class MainController {
    @FXML
    private Button btn;
    @FXML
    private Pane pane;
    @FXML
    private TreeTableView<Node<String>> treeTableView = new TreeTableView<Node<String>>();
    @FXML
    private TreeTableColumn<Node<String>, String> treeColumnTask = new TreeTableColumn<Node<String>, String>("Задачи");

    public MainController() {
        initTable();
    }

    @FXML
    private void initialize() {
        treeColumnTask.setCellValueFactory(new TreeItemPropertyValueFactory<Node<String>, String>("Задача"));
        treeTableView.getColumns().add(treeColumnTask);
        TreeItem<Node<String>> root = new TreeItem<Node<String>>(new Node<String>(1, "Не работа"));
        TreeItem<Node<String>> item = new TreeItem<Node<String>>(new Node<String>(2, "Чил"));
        root.getChildren().add(item);
        treeTableView.setRoot(root);
        treeColumnTask.setCellValueFactory(new TreeItemPropertyValueFactory<Node<String>, String>("value"));
    }

    @FXML
    public void initTable() {
        Tree tree = new Tree();
    }
}
