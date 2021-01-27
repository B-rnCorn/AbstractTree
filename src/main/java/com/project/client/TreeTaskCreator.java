package com.project.client;

import com.project.abstractTree.exceptions.TreeException;
import com.project.abstractTree.model.Node;
import com.project.abstractTree.model.Task;
import com.project.abstractTree.model.Tree;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class TreeTaskCreator {
    public static Tree<Task> createDefTask() {
        return new Tree<Task>(new Node<Task>(0, new Task(0, "Не работа")));
    }

    public static Tree<Task> create() {
        Tree<Task> taskTree = new Tree<Task>(new Node<Task>(0, new Task(0, "Не работа")));
        taskTree.add(0, new Node<Task>(1, new Task(1, "Просмотр")));
        taskTree.add(1, new Node<Task>(2, new Task(2, "Ютуб")));
        taskTree.add(1, new Node<Task>(3, new Task(3, "Фильмы")));
        taskTree.add(0, new Node<Task>(4, new Task(4, "Лежать")));
        taskTree.add(4, new Node<Task>(5, new Task(5, "На кровати")));
        taskTree.add(4, new Node<Task>(6, new Task(6, "На диване")));
        return taskTree;
    }

    public static Tree<Task> createFromFile(String filePath) {
        Tree<Task> taskTree = new Tree<Task>();
        try {
            taskTree.read(new FileReader(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TreeException e) {
            e.printStackTrace();
        }
        return taskTree;
    }

    public static Tree<Task> createFromReader(Reader reader) {
        Tree<Task> taskTree = new Tree<Task>();
        try {
            taskTree.read(reader);
        } catch (TreeException e) {
            e.printStackTrace();
        }
        return taskTree;
    }
}
