package com.project.abstract_tree;

import java.util.Collection;
import java.util.LinkedList;

import java.io.*;
import java.util.Iterator;

/**
 * Класс абстрактного дерева
 * @author Сергей
 * @author Андрей
 * @version 1.0
 */
public class Tree implements Serializable {
    /**
     * Поле для корня дерева
     */
    private Node root;
     /**
      *Конструктор
      * @param root - Узел, который будет корнем дерева
     */
    public Tree(Node root){
        this.root = root;
    }
    /**
     *Конструктор по умолчанию
     * @see Tree#Tree(Node)
     */
    public Tree(){
        this(new Node());
    }
     /**
     *метод get для корня
     */
    public Node getRoot(){
        return root;
    }
    /**
     *метод set для корня
     * @param root - узел для корня дерева
     */
    public void setRoot(Node root){
        this.root=root;
    }
     /**
     *метод удаление дерева
     */
    public void deleteTree(){
        this.root = null;
    }

    /**
     * Добавление узла к заданному Родителю
     * @param parentID - id родителя
     * @param addingNode - узел дерева для добавления
     * @return - возвращает результат добавления true-добавлено, false - недобавлено
     */
    public boolean add(int parentID, Node addingNode){
        Node parent = search(root, parentID);
        if(parent.getId()<addingNode.getId()) {
            parent.setChildren(addingNode);
            return true;
        }else return false;
    }
    /**
     * Удаление Узла
     * @param nodeID - id узла для удаления
     */
    public void delete(int nodeID){
        Node parent = search(root, nodeID).getParent();
        deleteSub(nodeID, parent);
    }

    /**
     * Удаление дочернего узла
     * @param subNodeID
     * @param node
     */
    public void deleteSub(int subNodeID, Node node){
        //Поиск Узла среди дочерних узлов Родителя
        Iterator iterator = node.getChildren().iterator();

        while(iterator.hasNext()){
            Node tmp = (Node) iterator.next();
            if(tmp.getId() == subNodeID) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Расщепление ветви дерева
     * @param splittingNode - узел для расщепления
     */
    public void split(Node splittingNode){
        int parentID = splittingNode.getParent().getId();

        //Добавить дочерние узлы Разделяемого Узла в список дочерних узлов Родительского Узла
        Collection<Node> list = splittingNode.getChildren();
        for (Node child : list){
            add(parentID, child);
        }

        //Удалить текущий узел из списка дочерних узлов Родителя
        deleteSub(splittingNode.getId(), splittingNode.getParent());
    }
    /**
     * Поиск узла по ключу
     * @param nodeID - id узла
     * @param tmp - вершина для рекурсивного поиска
     */
    public Node search(Node tmp, int nodeID){
        if (tmp == null) {
            return null;
        }

        if (tmp.getId() == nodeID) {
            return tmp;
        }
        Node temp;
            //Рекурсивно искать Узел среди дочерних узлов
            Collection<Node> list = tmp.getChildren();
            for (Node child : list) {
                temp=search(child,nodeID);
                if (temp!=null)return temp;
            }

        return null;
    }

    /**
     * Метод для сохранения дерева
     * @param fileName - имя файла для сохранения
     * @return - файл с деревом
     */
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

    /**
     * Метод для загрузки дерева с файла
     * @param fileName - имя файла для загрузки
     * @return - дерево, считаннок из файла
     */
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

    /**
     * Метод добавления узла/ветви дерева по id
     * @param nodeId - id узла, в который добавляется ущел/ветвь
     * @param nodeAdd - узел/ветвь для добавления
     */
    //Добавление узла/ветви дерева
    public void addBranch(int nodeId,Node nodeAdd){
        Node destinationNode=search(new Node(),nodeId);
        destinationNode.setChildren(nodeAdd);
        nodeAdd.setParent(destinationNode);
    }

    /**
     * Метод удаления узла по id
     * @param nodeId - di узла, который нужно удалить
     */
    //Удаление узла/ветви дерева
    public void deleteBranch(int nodeId){
        Node delNode=search(new Node(),nodeId);
        Collection<Node> parentChildren =delNode.getParent().getChildren();
        Node tempNode;
        /*
        for(int i=0;i<parentChildren.size();i++) {
            tempNode = parentChildren.get(i);
            if (tempNode.getId() == nodeId) parentChildren.remove(i);
        }*/
        int i=0;
        for(Node temp:parentChildren){
            if (temp.getId() == nodeId) parentChildren.remove(i);
            i++;
        }
    }

    /**
     * Метод для клонирования дерева
     * @param tmp - промежуточная переменная для рекурсивного клонирования дерева
     * @return  - возвращает клонированное дерево
     */
    //Клонирование дерева
    private Node clone(Node tmp){
        if (tmp == null) {
            return null;
        }else
        {
            Collection<Node> list = tmp.getChildren();
            for (Node child : list){
                return clone(child.clone());
            }
        }
        return null;
    }
    public Tree clone(){
        return new Tree(clone(this.getRoot()));
    }
    //Упорядочивание дерева
    /*public Tree orderTree(){
        return new Tree(orderTree(this.root));
    }
    private Node orderTree(Node tmp){
        if (tmp == null) {
            return null;
        }else
        {
            LinkedList<Node> list = tmp.getChildren();
            for (Node child : list){
                if(child.getIdNode()<tmp.getIdNode())deleteBranch(child.getIdNode());
                else return orderTree(child.cloneNode());
            }
        }
        return null;
    }*/
    //вывод дерева на консоль
    public void outputTree(Node tmp){
        if (tmp != null&&tmp.getParent()==null){
            System.out.println("ID: "+tmp.getId()+" Value: "+tmp.getValue());
        }
        if (tmp != null&&tmp.getParent()!=null) {
            System.out.println("ID: "+tmp.getId()+" Value: "+tmp.getValue()+" Parent: "+tmp.getParent().getId());
        }
        //Рекурсивно искать Узел среди дочерних узлов
        Collection<Node> list = tmp.getChildren();
        for (Node child : list) {
            outputTree(child);
        }
    }
}