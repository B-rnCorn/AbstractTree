package com.project.abstract_tree;

import com.fasterxml.jackson.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * Class of tree node
 * @author Sergey
 * @author Andrey
 * @version 1.0
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Node <T>{
    /**
     * Field for node id
     */
    private int id;
    /**
     * Field for node value
     */
    private T value;
    /**
     * Field for nodes children
     */
    private Collection<Node> children;
    /**
     * Field for parent node
     */
    private Node parent;
    /**
     *Constructor for creating node
     * @param id - id node
     * @param parent - parent node
     * @param value - value of node
     */
    public Node(int id,T value,Node parent){
        this.id =id;
        this.value=value;
        this.parent=parent;
        this.children = new LinkedList<Node>();
    }

    /**
     * Constructor for creating root node
     * @param id
     * @param value
     * @see Node#Node(int, Object, Node)
     */
    public Node(int id, T value){
        this(id, value, null);
    }

    /**
     * Default Constructor
     * @see Node#Node(int, Object, Node)
     */
    public Node(){
        this(0, (T) new Object());
    }

    /**
     * Method for adding children node
     * @param idNode - id of adding node
     * @param value - value of adding node
     * @param parent - node to witch we add
     */
    public void addChildren(int idNode, T value, Node parent){
        children.add(new Node(idNode,value,parent));
    }

    /**
     * Method for adding children node
     * @param node - children node
     */
    public void addChildren(Node node){
        node.setParent(this);
        children.add(node);
    }

    /**
     * Method for adding collection of nodes
     * @param children - collection of nodes
     */
    public void addChildren(Collection<Node> children){
        this.children=children;
        for (Node node:children) {
            node.setParent(this);
        }
    }

    /**
     * Method for getting children nodes of current node
     * @return - children nodes
     */
    public Collection<Node> getChildren(){
        return children;
    }

    /**
     * Method for getting id of current node
     * @return id node
     */
    public int getId(){
        return id;
    }

    /**
     * Method for setting id of current node
     * @param id - id of node
     */
    public void setId(int id){
        this.id =id;
    }

    /**
     * Method for getting values of current node
     * @return - value of node
     */
    public T getValue() {
        return value;
    }

    /**
     * Method for setting values of current node
     * @param value - value of node
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Method for getting parent node of current node
     * @return - parent node
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Method for setting parent node of current node
     * @param parent - parent node
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }
    /**
     *Method for cloning node
     * @return cloned node
     */
    public Node clone(){
        Node cloned= new Node (this.id,this.value);
        Node temp=null;
        for(Node child:children) {
            temp=child.clone();
            temp.setParent(cloned);
            cloned.addChildren(temp);
        }
        return cloned;
    }
}
