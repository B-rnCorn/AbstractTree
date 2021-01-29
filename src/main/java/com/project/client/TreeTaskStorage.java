package com.project.client;

import com.project.abstractTree.model.Task;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class TreeTaskStorage {
    public void saveTree(TreeTableView<Task> treeTableView) {
        ObservableList<TreeTableColumn<Task, ?>> list = treeTableView.getColumns();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
    }
}
