package com.project.abstract_tree.app.controller;

import com.project.abstract_tree.model.Node;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    public void initTable() {
        treeColumnTask.setCellValueFactory(new TreeItemPropertyValueFactory<Node<String>, String>("Задача"));
        treeTableView.getColumns().add(treeColumnTask);
    }
}
