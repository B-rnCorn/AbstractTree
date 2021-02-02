package com.project.client.userInterface;

import com.project.abstractTree.model.Task;
import com.project.abstractTree.model.Tree;
import com.project.client.ClientErrorMessage;
import com.project.client.treeManagement.TreeTaskReflector;
import com.project.client.treeManagement.TreeTaskStorage;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class TaskEdit {
    private Button btnEditTask;
    private TreeTableView<Task> treeTableView;
    private Pane paneEdit;
    private TextField textField;
    private Button btnSubmit;
    private Tree<Task> taskTree;

    public TaskEdit(TreeTableView<Task> treeTableView, Button btnEditTask, Pane paneEdit, TextField textField, Button btnSubmit) {
        this.treeTableView = treeTableView;
        this.btnEditTask = btnEditTask;
        this.paneEdit = paneEdit;
        this.textField = textField;
        this.btnSubmit = btnSubmit;
        showEditTaskPanel();
        addEventHandlerOnSubmitButton();
    }

    private void showEditTaskPanel() {
        btnEditTask.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> paneEdit.setVisible(true));
    }

    private void addEventHandlerOnSubmitButton() {
        btnSubmit.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            try {
                TreeItem<Task> editItem = treeTableView.getTreeItem(treeTableView.getEditingCell().getRow());
                Task task = editItem.getValue();
                task.setName(textField.getText());
                int taskId = editItem.getValue().getId();
                Tree<Task> taskTree = TreeTaskStorage.getTreeTask(treeTableView);
                taskTree.search(taskId).setValue(task);
                TreeTaskReflector.showTree(treeTableView, taskTree);
                paneEdit.setVisible(false);
            } catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(ClientErrorMessage.CHOOSE_ERROR_HEADER);
                alert.setTitle(ClientErrorMessage.CHOOSE_ERROR);
                alert.show();
            }
        });
    }
}
