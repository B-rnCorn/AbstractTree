package com.project.client;

import com.project.abstractTree.model.Node;
import com.project.abstractTree.model.Task;
import com.project.abstractTree.model.Tree;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

public class TreeTaskStorage {
    public static Tree<Task> getTreeTask(TreeTableView<Task> treeTableView) {
        Tree<Task> taskTree = new Tree<Task>(new Node<Task>(treeTableView.getRoot().getValue().getId(), treeTableView.getRoot().getValue()));
        copyNode(treeTableView.getRoot(), taskTree.getRoot());
        return taskTree;
    }

    private static void copyNode(TreeItem<Task> treeItem, Node<Task> node) {
        int i = 0;
        Node<Task> tempNode;
        for (TreeItem<Task> temp : treeItem.getChildren()) {
            tempNode = new Node<Task>(temp.getValue().getId(), temp.getValue(), node);
            node.addChildren(tempNode);
            copyNode(temp, tempNode);
            i++;
        }
    }

    public void saveTree(TreeTableView<Task> treeTableView) {
        ObservableList<TreeTableColumn<Task, ?>> list = treeTableView.getColumns();
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
    }
}
