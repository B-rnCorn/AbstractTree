package com.project.abstract_tree;

import java.util.LinkedList;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

public class Tree implements Serializable {

    private Node root;  // Корень дерева

    //Конструкторы
    public Tree(Node root){
        this.root = root;
    }

    public Tree(){
        this(new Node());
    }
    //get/set для корня
    public Node getRoot(){
        return root;
    }
    public void setRoot(Node root){
        this.root=root;
    }
    // Методы
    //Удаление дерева
    public void deleteTree(){
        this.root = null;
    }

    //Добавление Узла к заданному Родителю
    public void add(int parentID, Node addingNode){
        Node parent = search(root, parentID);
        parent.setChildren(addingNode);
    }

    //Удаление Узла
    public void delete(int nodeID){
        Node parent = search(root, nodeID).getParent();
        deleteSubNode(nodeID, parent);
    }

    public void deleteSubNode(int subNodeID, Node node){
        //Поиск Узла среди дочерних узлов Родителя
        Iterator iterator = node.getChildren().iterator();

        while(iterator.hasNext()){
            Node tmp = (Node) iterator.next();
            if(tmp.getIdNode() == subNodeID) {
                iterator.remove();
                break;
            }
        }
    }

    // Расщепление ветви дерева
    public void splitNode(Node splitingNode){
        int parentID = splitingNode.getParent().getIdNode();

        //Добавить дочерние узлы Разделяемого Узла в список дочерних узлов Родительского Узла
        LinkedList<Node> list = splitingNode.getChildren();
        for (Node child : list){
            add(parentID, child);
        }

        //Удалить текущий узел из списка дочерних узлов Родителя
        deleteSubNode(splitingNode.getIdNode(), splitingNode.getParent());
    }

    //Поиск Узла по ключу
    public Node search(Node tmp, int nodeID){
        if (tmp == null) {
            return null;
        }

        if (tmp.getIdNode() == nodeID) {
            return tmp;
        }

        //Рекурсивно искать Узел среди дочерних узлов
        LinkedList<Node> list = tmp.getChildren();
        for (Node child : list){
            return search(child, nodeID);
        }
        return null;
    }

    //Сохранение Дерева в файл на диск
    public File serializeToFile(String fileName){
        File treeFile = new File(fileName);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(treeFile))){
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return treeFile;
        }
    }

    //Загрузка Дерева из файла с диска
    public Tree deserializeFromFile(String fileName){
        Tree deserialTree = null;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))){
            deserialTree = (Tree) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            return deserialTree;
        }
    }

    //Добавление узла/ветви дерева
    public void addBranch(int nodeId,Node nodeAdd){
        Node destinationNode=search(new Node(),nodeId);
        destinationNode.setChildren(nodeAdd);
        nodeAdd.setParent(destinationNode);
    }
    //Удаление узла/ветви дерева
    public void deleteBranch(int nodeId){
        Node delNode=search(new Node(),nodeId);
        LinkedList<Node> parentChildren =delNode.getParent().getChildren();
        Node tempNode;
        for(int i=0;i<parentChildren.size();i++) {
            tempNode = parentChildren.get(i);
            if (tempNode.getIdNode() == nodeId) parentChildren.remove(i);
        }
    }
    //Клонирование дерева
    private Node cloneTree(Node tmp){
        if (tmp == null) {
            return null;
        }else
        {
            LinkedList<Node> list = tmp.getChildren();
            for (Node child : list){
                return cloneTree(child.cloneNode());
            }
        }
        return null;
    }
    public Tree cloneTree(){
        return new Tree(cloneTree(this.getRoot()));
    }
    //Упорядочивание дерева
    public Tree orderTree(){
        return new Tree(orderTree(this.root));
    }
    private Node orderTree(Node tmp){
        if (tmp == null) {
            return null;
        }else
        {
            LinkedList<Node> list = tmp.getChildren();
            for (Node child : list){
                if(child.getIdNode()>tmp.getIdNode())deleteBranch(child.getIdNode());
                else return orderTree(child.cloneNode());
            }
        }
        return null;
    }
}