package com.project.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.abstractTree.exceptions.TreeException;
import com.project.abstractTree.model.Node;
import com.project.abstractTree.model.Tree;
import com.project.server.model.Task;
import javafx.scene.Parent;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TreeService {

    // Переменная для хранения дерева
    private static final Tree<Task> TASK_TREE = new Tree<>();

    // Переменная для генерирования ID задачи
    public static int TASK_ID_HOLDER;

    public TreeService() {
        try {
            TASK_TREE.read(new FileReader("src/main/resources/treeStorageFile.json"));
            TASK_ID_HOLDER = TASK_TREE.getMaxId();
        } catch (TreeException | FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Tree<Task> create(int parentId, String taskName) {
        final int taskId = ++TASK_ID_HOLDER;
        TASK_TREE.setMaxId(TASK_ID_HOLDER);

        Task newTask = new Task(taskId, taskName);
        if (TASK_TREE.add(parentId, new Node<>(taskId, newTask))) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.writeValue(new FileWriter("src/main/resources/treeStorageFile.json"), TASK_TREE);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return TASK_TREE;
        }
        return null;
    }

    public Tree<Task> readAll() {
        return TASK_TREE;
    }

    public Node<Task> read(int id) {
        return TASK_TREE.search(id);
    }

    public boolean update(int id, Task task) {
        Node<Task> taskNode = TASK_TREE.search(id);

        if (taskNode != null && id >= 0) {

            ObjectMapper mapper = new ObjectMapper();
            try {
                taskNode.setValue(task);
                mapper.writeValue(new FileWriter("src/main/resources/treeStorageFile.json"), TASK_TREE);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public Node<Task> delete(int id, boolean type) {
        int parentId = TASK_TREE.search(id).getParent().getId();

        if (TASK_TREE.search(id) != null && id > 0) {

            if (type) TASK_TREE.splitById(id);
            else TASK_TREE.removeNodeById(id);

            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.writeValue(new FileWriter("src/main/resources/treeStorageFile.json"), TASK_TREE);
                return TASK_TREE.search(parentId);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
