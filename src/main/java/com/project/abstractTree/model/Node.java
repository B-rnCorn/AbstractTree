package com.project.abstractTree.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Class of tree node
 *
 * @author Sergey
 * @author Andrey
 * @version 1.0.0
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Node<T> implements Cloneable {
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
    private Collection<Node<T>> children;
    /**
     * Field for parent node
     */
    private Node<T> parent;

    /**
     * Constructor for creating node
     *
     * @param id     - id node
     * @param parent - parent node
     * @param value  - value of node
     */
    public Node(int id, T value, Node<T> parent) {
        this.id = id;
        this.value = value;
        this.parent = parent;
        this.children = new LinkedList<Node<T>>();
    }

    /**
     * Constructor for creating root node
     *
     * @param id    - id of node
     * @param value - value of node
     * @see Node#Node(int, Object, Node)
     */
    public Node(int id, T value) {
        this(id, value, null);
    }

    /**
     * Default Constructor
     *
     * @see Node#Node(int, Object, Node)
     */
    public Node() {
        this(0, (T) new Object());
    }

    /**
     * Method for adding children node
     *
     * @param idNode - id of adding node
     * @param value  - value of adding node
     * @param parent - node to witch we add
     */
    public void addChildren(int idNode, T value, Node<T> parent) {
        children.add(new Node<T>(idNode, value, parent));
    }

    /**
     * Method for adding children node
     *
     * @param node - children node
     */
    public void addChildren(Node<T> node) {
        node.setParent(this);
        children.add(node);
    }

    /**
     * Method for adding collection of nodes
     *
     * @param children - collection of nodes
     */
    public void addChildren(Collection<Node<T>> children) {
        this.children = children;
        for (Node<T> node : children) {
            node.setParent(this);
        }
    }

    /**
     * Method for getting children nodes of current node
     *
     * @return - children nodes
     */
    public Collection<Node<T>> getChildren() {
        return children;
    }

    /**
     * Method for getting id of current node
     *
     * @return id node
     */
    public int getId() {
        return id;
    }

    /**
     * Method for setting id of current node
     *
     * @param id - id of node
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method for getting values of current node
     *
     * @return - value of node
     */
    public T getValue() {
        return value;
    }

    /**
     * Method for setting values of current node
     *
     * @param value - value of node
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Method for getting parent node of current node
     *
     * @return - parent node
     */
    public Node<T> getParent() {
        return parent;
    }

    /**
     * Method for setting parent node of current node
     *
     * @param parent - parent node
     */
    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    /**
     * Method for cloning node
     *
     * @return cloned node
     */
    public Node<T> clone() {
        Node<T> cloned = new Node<T>(this.id, this.value, this.parent);
        Node<T> temp;
        for (Node<T> child : children) {
            temp = child.clone();
            temp.setParent(cloned);
            cloned.addChildren(temp);
        }
        return cloned;
    }
}
