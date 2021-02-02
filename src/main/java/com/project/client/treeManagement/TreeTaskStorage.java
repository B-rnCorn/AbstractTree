package com.project.client.treeManagement;

import com.project.abstractTree.model.Node;
import com.project.abstractTree.model.Task;
import com.project.abstractTree.model.Tree;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;

public class TreeTaskStorage {
    public static Tree<Task> getTreeTask(TreeTableView<Task> treeTableView) {
        Tree<Task> taskTree = new Tree<>(new Node<>(treeTableView.getRoot().getValue().getId(), treeTableView.getRoot().getValue()));
        copyNode(treeTableView.getRoot(), taskTree.getRoot());
        return taskTree;
    }

    private static void copyNode(TreeItem<Task> treeItem, Node<Task> node) {
        int i = 0;
        Node<Task> tempNode;
        for (TreeItem<Task> temp : treeItem.getChildren()) {
            tempNode = new Node<>(temp.getValue().getId(), temp.getValue(), node);
            node.addChildren(tempNode);
            copyNode(temp, tempNode);
            i++;
        }
    }

    public static void saveTree(TreeTableView<Task> treeTableView) {
        /*
        try {
            final URL url = new URL("localhost");
            final HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
        }catch (MalformedURLException e){

        }catch (IOException e){

        }*/
    }
}
