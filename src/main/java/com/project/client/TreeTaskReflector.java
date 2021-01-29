package com.project.client;

import com.project.abstractTree.model.Node;
import com.project.abstractTree.model.Task;
import com.project.abstractTree.model.Tree;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.CheckBoxTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;

public class TreeTaskReflector {
    private SimpleBooleanProperty choice;

    public void createTaskTreeTableView(Tree<Task> taskTree, TreeTableView<Task> treeTableView) {
        treeTableView.setEditable(true);
        TreeTableColumn<Task, String> treeColumnTask = new TreeTableColumn<Task, String>("Задачи");
        TreeTableColumn<Task, Boolean> treeColumnActive = new TreeTableColumn<Task, Boolean>("Активность");
        TreeTableColumn<Task, String> treeColumnActiveTime = new TreeTableColumn<Task, String>("Время активности");
        treeColumnTask.setMinWidth(150);
        treeColumnActive.setMinWidth(150);
        treeColumnActiveTime.setMinWidth(150);
        treeTableView.getColumns().add(treeColumnTask);
        treeTableView.getColumns().add(treeColumnActive);
        treeTableView.getColumns().add(treeColumnActiveTime);
    }

    public void showTreeInTreeTableView(Tree<Task> taskTree, TreeTableView<Task> treeTableView) {
        TreeTableColumn<Task, String> treeColumnTask = (TreeTableColumn<Task, String>) treeTableView.getColumns().get(0);
        TreeTableColumn<Task, Boolean> treeColumnActive = (TreeTableColumn<Task, Boolean>) treeTableView.getColumns().get(1);
        TreeTableColumn<Task, String> treeColumnActiveTime = (TreeTableColumn<Task, String>) treeTableView.getColumns().get(2);
        TreeItem<Task> root = showTree(taskTree.getRoot());
        treeTableView.setRoot(root);
        setColumnTaskValueFactory(treeColumnTask);
        setColumnActiveValueFactory(treeTableView, treeColumnActive);
        setColumnActiveCellFactory(treeColumnActive);
        setColumnActiveTimeValueFactory(treeColumnActiveTime);
    }

    private TreeItem<Task> showTree(Node<Task> taskNode) {
        TreeItem<Task> root = new TreeItem<Task>(taskNode.getValue());
        int i = 0;
        for (Node<Task> temp : taskNode.getChildren()) {
            root.getChildren().add(showTree(temp));
            i++;
        }
        return root;
    }
    private void setColumnTaskValueFactory(TreeTableColumn<Task, String> treeColumnTask) {
        treeColumnTask.setCellValueFactory(new TreeItemPropertyValueFactory<Task, String>("name"));
    }

    private void setColumnActiveValueFactory(final TreeTableView<Task> treeTableView, final TreeTableColumn<Task, Boolean> treeColumnActive) {
        treeColumnActive.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Task, Boolean>, ObservableValue<Boolean>>() {
            public ObservableValue<Boolean> call(TreeTableColumn.CellDataFeatures<Task, Boolean> param) {
                final TreeItem<Task> treeItem = param.getValue();
                final Task task = treeItem.getValue();
                final SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(task.isActive());
                booleanProp.addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue) {
                            task.Activate();
                            if (choice != null) choice.set(false);
                            choice = booleanProp;
                        } else {
                            task.Deactivate();
                            treeTableView.refresh();
                        }
                    }
                });
                return booleanProp;
            }
        });
    }

    private void setColumnActiveCellFactory(TreeTableColumn<Task, Boolean> treeColumnActive) {
        treeColumnActive.setCellFactory(new Callback<TreeTableColumn<Task, Boolean>, TreeTableCell<Task, Boolean>>() {
            public TreeTableCell<Task, Boolean> call(TreeTableColumn<Task, Boolean> param) {
                CheckBoxTreeTableCell<Task, Boolean> cell = new CheckBoxTreeTableCell<Task, Boolean>();
                cell.setEditable(true);
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
    }

    private void setColumnActiveTimeValueFactory(TreeTableColumn<Task, String> treeColumnActiveTime) {
        treeColumnActiveTime.setCellValueFactory(new TreeItemPropertyValueFactory<Task, String>("timeDayActivity"));
    }
}
