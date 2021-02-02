package com.project.client.userInterface;

import com.project.abstractTree.model.Node;
import com.project.abstractTree.model.Task;
import com.project.abstractTree.model.Tree;
import com.project.client.ClientErrorMessage;
import com.project.client.treeManagement.TreeTaskReflector;
import com.project.client.treeManagement.TreeTaskStorage;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class TaskAdd {
    private Button btnAddTask;
    private TreeTableView<Task> treeTableView;
    private Pane paneAdd;
    private TextField textFieldAddTaskName;
    private Button btnSubmitAdd;

    public TaskAdd(TreeTableView<Task> treeTableView, Button btnAddTask, Pane paneAdd, TextField textFieldAddTaskName, Button btnSubmitAdd) {
        this.treeTableView = treeTableView;
        this.btnAddTask = btnAddTask;
        this.paneAdd = paneAdd;
        this.textFieldAddTaskName = textFieldAddTaskName;
        this.btnSubmitAdd = btnSubmitAdd;
        showAddTaskPanel();
        addEventHandlerOnSubmitButton();
    }

    private void showAddTaskPanel() {
        btnAddTask.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> paneAdd.setVisible(true));
    }

    private void addEventHandlerOnSubmitButton() {
        btnSubmitAdd.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            try {
                TreeItem<Task> parentItem = treeTableView.getTreeItem(treeTableView.getEditingCell().getRow());
                Task parentTask = parentItem.getValue();
                int taskId = parentItem.getValue().getId();
                Tree<Task> taskTree = TreeTaskStorage.getTreeTask(treeTableView);
                taskTree.search(taskId).addChildren(new Node<Task>(taskId * 3, new Task(taskId * 3, textFieldAddTaskName.getText()), taskTree.search(taskId)));
                TreeTaskReflector.showTree(treeTableView, taskTree);
                paneAdd.setVisible(false);
            } catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(ClientErrorMessage.CHOOSE_ERROR_HEADER);
                alert.setTitle(ClientErrorMessage.CHOOSE_ERROR);
                alert.show();
            }
        });
    }
}
