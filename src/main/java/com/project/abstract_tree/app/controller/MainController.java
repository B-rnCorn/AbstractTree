package com.project.abstract_tree.app.controller;

import javafx.fxml.FXML;
import com.project.abstract_tree.model.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class MainController {
    //@FXML private AnchorPane anchorPane=new AnchorPane();
    @FXML private TreeTableView<Node> treeTableView=new TreeTableView<Node>();
    @FXML private TreeTableColumn<Node,String> treeColumnTask=new TreeTableColumn<Node, String>("Задачи");

    public void initTable(){
        treeColumnTask.setCellValueFactory(new TreeItemPropertyValueFactory<Node,String>("Задача"));
    }
}
