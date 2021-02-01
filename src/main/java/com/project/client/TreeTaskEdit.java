package com.project.client;

import com.project.abstractTree.model.Task;
import com.project.abstractTree.model.Tree;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class TreeTaskEdit {
    private Button btnEditTask;
    private TreeTableView<Task> treeTableView;
    private Pane paneEdit;
    private TextField textField;
    private Button btnSubmit;
    private Tree<Task> taskTree;

    public TreeTaskEdit(TreeTableView<Task> treeTableView, Button btnEditTask, Pane paneEdit, TextField textField, Button btnSubmit) {
        this.treeTableView = treeTableView;
        this.btnEditTask = btnEditTask;
        this.paneEdit = paneEdit;
        this.textField = textField;
        this.btnSubmit = btnSubmit;
        showEditTaskPanel();
        addEventHandlerOnSubmitButton();
    }

    private void showEditTaskPanel() {
        btnEditTask.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle(MouseEvent event) {
                paneEdit.setVisible(true);
            }
        });
    }

    private void addEventHandlerOnSubmitButton() {
        btnSubmit.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle(MouseEvent event) {
                TreeItem<Task> editItem = treeTableView.getTreeItem(treeTableView.getEditingCell().getRow());
                Task task = editItem.getValue();
                task.setName(textField.getText());
                int taskId = editItem.getValue().getId();
                Tree<Task> taskTree = TreeTaskStorage.getTreeTask(treeTableView);
                taskTree.search(taskId).setValue(task);
                TreeTaskReflector.showTree(treeTableView, taskTree);
                paneEdit.setVisible(false);
            }
        });
    }
}
