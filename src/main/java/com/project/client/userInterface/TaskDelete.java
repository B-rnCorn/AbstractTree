package com.project.client.userInterface;

import com.project.abstractTree.model.Task;
import com.project.abstractTree.model.Tree;
import com.project.client.ClientErrorMessage;
import com.project.client.treeManagement.TreeTaskReflector;
import com.project.client.treeManagement.TreeTaskStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class TaskDelete {
    private final Button btnDeleteTask;
    private final TreeTableView<Task> treeTableView;
    private final Pane paneDelete;
    private final ChoiceBox<String> choiceBoxDeleteType;
    private final Button btnSubmitDelete;
    private boolean deleteNode;

    public TaskDelete(TreeTableView<Task> treeTableView, Button btnEditTask, Pane paneDelete, ChoiceBox<String> choiceBoxDeleteType, Button btnSubmitDelete) {
        this.treeTableView = treeTableView;
        this.btnDeleteTask = btnEditTask;
        this.paneDelete = paneDelete;
        this.choiceBoxDeleteType = choiceBoxDeleteType;
        this.btnSubmitDelete = btnSubmitDelete;
        showDeleteTaskPanel();
        initCheckBox();
        addEventHandlerOnSubmitButton();
    }

    private void showDeleteTaskPanel() {
        btnDeleteTask.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> paneDelete.setVisible(true));
    }

    private void initCheckBox() {
        String deleteType1 = "Удалить вершину";
        String deleteType2 = "Удалить ветвь";
        Separator separator = new Separator();
        ObservableList<String> languages = FXCollections.observableArrayList(deleteType1, deleteType2);
        choiceBoxDeleteType.setItems(languages);
        choiceBoxDeleteType.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            deleteNode = newValue.intValue() == 0;
        });
    }

    private void addEventHandlerOnSubmitButton() {
        btnSubmitDelete.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            try {
                TreeItem<Task> parentItem = treeTableView.getTreeItem(treeTableView.getEditingCell().getRow());
                Task parentTask = parentItem.getValue();
                int taskId = parentItem.getValue().getId();
                if (treeTableView.getEditingCell().getRow() != 0) {
                    Tree<Task> taskTree = TreeTaskStorage.getTreeTask(treeTableView);
                    if (deleteNode) {
                        taskTree.splitById(taskId);
                    } else {
                        taskTree.removeNodeById(taskId);
                    }
                    TreeTaskReflector.showTree(treeTableView, taskTree);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(ClientErrorMessage.DELETE_ROOT_TASK_ERROR);
                    alert.setTitle(ClientErrorMessage.DELETE_ERROR);
                    alert.show();
                }
                paneDelete.setVisible(false);
            } catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(ClientErrorMessage.CHOOSE_ERROR_HEADER);
                alert.setTitle(ClientErrorMessage.CHOOSE_ERROR);
                alert.show();
            }
        });
    }
}
