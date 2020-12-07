package com.project.abstract_tree.core;

import java.util.LinkedList;

public class Node <T> {
    private int idNode;
    private T value;
    private LinkedList<Node> leftChildrens;
    private LinkedList<Node> rightChildrens;
    public Node(int idNode,T value){
        this.idNode=idNode;
        this.value=value;
    }
    public void setChildren(int idNode,T value){
        if(idNode>=this.idNode)leftChildrens.add(new Node(idNode,value));
        else rightChildrens.add(new Node(idNode,value));
    }
    public void setChildren(Node node){
        if(node.getIdNode()>=this.idNode)leftChildrens.add(node);
        else rightChildrens.add(node);
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
}
