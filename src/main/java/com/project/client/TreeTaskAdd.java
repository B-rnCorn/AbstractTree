package com.project.client;

import com.project.abstractTree.model.Node;
import com.project.abstractTree.model.Task;
import com.project.abstractTree.model.Tree;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class TreeTaskAdd {
    private Button btnAddTask;
    private TreeTableView<Task> treeTableView;
    private Pane paneAdd;
    private TextField textFieldAddTaskName;
    private Button btnSubmitAdd;

    public TreeTaskAdd(TreeTableView<Task> treeTableView, Button btnAddTask, Pane paneAdd, TextField textFieldAddTaskName, Button btnSubmitAdd) {
        this.treeTableView = treeTableView;
        this.btnAddTask = btnAddTask;
        this.paneAdd = paneAdd;
        this.textFieldAddTaskName = textFieldAddTaskName;
        this.btnSubmitAdd = btnSubmitAdd;
        showAddTaskPanel();
        addEventHandlerOnSubmitButton();
    }

    private void showAddTaskPanel() {
        btnAddTask.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                paneAdd.setVisible(true);
            }
        });
    }

    private void addEventHandlerOnSubmitButton() {
        btnSubmitAdd.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle(MouseEvent event) {
                TreeItem<Task> parentItem = treeTableView.getTreeItem(treeTableView.getEditingCell().getRow());
                Task parentTask = parentItem.getValue();
                int taskId = parentItem.getValue().getId();
                Tree<Task> taskTree = TreeTaskStorage.getTreeTask(treeTableView);
                taskTree.search(taskId).addChildren(new Node<Task>(taskId * 3, new Task(taskId * 3, textFieldAddTaskName.getText()), taskTree.search(taskId)));
                TreeTaskReflector.showTree(treeTableView, taskTree);
                paneAdd.setVisible(false);
            }
        });
    }
}
