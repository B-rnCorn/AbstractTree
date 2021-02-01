package com.project.client;

import com.project.abstractTree.model.Task;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
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

    public void showProperty(TreeTableView<Task> treeTableView, final TreeItem<Task> editItem) {
        paneProperty.setVisible(true);
        labelTaskName.setText(editItem.getValue().getName());
        labelTaskTime.setText(Long.toString(editItem.getValue().getTimeDayActivity()));

    }
}
