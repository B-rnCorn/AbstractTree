package com.project.abstract_tree;

import java.util.LinkedList;

public class Node <T> {
    private int idNode;
    private T value;
    private LinkedList<Node> childrens;
    private Node parent;

    // Конструкторы
    public Node(int idNode,T value,Node parent){
        this.idNode=idNode;
        this.value=value;
        this.parent=parent;
    }

    public void Node(int idNode,T value){
        this(idNode, value, null);
    }

    //Геттреры и сеттеры атрибутов
    public void setChildren(int idNode,T value,Node parent){
        childrens.add(new Node(idNode,value,parent));
    }

    public void setChildren(Node node){
        childrens.add(node);
    }

    public LinkedList<Node> getChildren(){
        return childrens;
    }

    public int getIdNode(){
        return idNode;
    }

    public void setIdNode(int idNode){
        this.idNode=idNode;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
