package com.project.client;

import com.project.abstractTree.model.Task;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class TaskInformationDisplay {
    private Pane paneProperty;
    private Label labelTaskName;
    private Label labelTaskTime;

    public TaskInformationDisplay(Pane paneProperty, Label labelTaskName, Label labelTaskTime) {
        this.paneProperty = paneProperty;
        this.labelTaskName = labelTaskName;
        this.labelTaskTime = labelTaskTime;
    }

    public void showProperty(TreeTableView<Task> treeTableView) {
        final TreeItem<Task> editItem = treeTableView.getTreeItem(treeTableView.getEditingCell().getRow());
        treeTableView.getColumns().get(treeTableView.getEditingCell().getRow()).addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                paneProperty.setVisible(true);
                labelTaskName.setText(editItem.getValue().getName());
                labelTaskTime.setText(Long.toString(editItem.getValue().getTimeDayActivity()));
            }
        });
    }
}
