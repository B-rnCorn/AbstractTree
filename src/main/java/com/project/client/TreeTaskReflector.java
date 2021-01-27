package com.project.client;

import com.project.abstractTree.model.Node;
import com.project.abstractTree.model.Task;
import com.project.abstractTree.model.Tree;
import javafx.geometry.Pos;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.CheckBoxTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;

public class TreeTaskReflector {
    public void showTreeInTreeTableView(Tree<Task> taskTree, TreeTableView<Task> treeTableView) {
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
        TreeItem<Task> item = new TreeItem<Task>();
        showNodes(taskTree.getRoot(), item);
        treeTableView.setRoot(item);
        treeColumnTask.setCellValueFactory(new TreeItemPropertyValueFactory<Task, String>("name"));
        /*treeColumnActive.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Task, Boolean>, ObservableValue<Boolean>>() {
            public ObservableValue<Boolean> call(TreeTableColumn.CellDataFeatures<Task, Boolean> param) {
                TreeItem<Task> treeItem = param.getValue();
                final Task emp = treeItem.getValue();
                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(emp.isActive());

                booleanProp.addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue) {
                            emp.Activate();
                        } else {
                            emp.Deactivate();
                        }
                    }
                });
                return booleanProp;
            }
        });*/
        treeColumnActive.setCellFactory(new Callback<TreeTableColumn<Task, Boolean>, TreeTableCell<Task, Boolean>>() {
            public TreeTableCell<Task, Boolean> call(TreeTableColumn<Task, Boolean> param) {
                CheckBoxTreeTableCell<Task, Boolean> cell = new CheckBoxTreeTableCell<Task, Boolean>();
                cell.setEditable(true);
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
        treeColumnActiveTime.setCellValueFactory(new TreeItemPropertyValueFactory<Task, String>("timeDayActivity"));
    }

    private void showNodes(Node<Task> taskNode, TreeItem<Task> item) {
        item = new TreeItem<Task>(taskNode.getValue());
        int i = 0;
        for (Node<Task> temp : taskNode.getChildren()) {
            item.getChildren().add(new TreeItem<Task>(temp.getValue()));
            showNodes(temp, item.getChildren().get(i));
        }
    }
}
