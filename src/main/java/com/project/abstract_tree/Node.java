package com.project.abstract_tree;

import com.fasterxml.jackson.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * Класс узла дерева
 * @author Сергей
 * @author Андрей
 * @version 1.0
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Node <T>{
    /**
     * Поле для хранения id узла
     */
    private int id;
    /**
     * Поле для хранения значения узла
     */
    private T value;
    /**
     * Поле для хранения дчоерних узлов
     */
    private Collection<Node> children;
    /**
     * Поле для одительского узла текущей вершины
     */
    private Node parent;
    /**
     *Конструктор
     * @param id - id узла
     * @param parent - родительский узел
     * @param value - значение узла
     */
    public Node(int id,T value,Node parent){
        this.id =id;
        this.value=value;
        this.parent=parent;
        this.children = new LinkedList<Node>();
    }

    /**
     * Конструктор для создание узла-корня
     * @param id
     * @param value
     * @see Node#Node(int, Object, Node)
     */
    public Node(int id, T value){
        this(id, value, null);
    }

    /**
     * Конструктор по умолчанию
     * @see Node#Node(int, Object, Node)
     */
    public Node(){
        this(0, (T) new Object());
    }

    /**
     * Метод добавления дочернего узла
     * @param idNode - id узла, который добавляем
     * @param value - значение добавляемого узла
     * @param parent - узел, которому добавляем дочерний узел
     */
    public void addChildren(int idNode, T value, Node parent){
        children.add(new Node(idNode,value,parent));
    }

    /**
     * Метод добавления дочернего узла к текущему узлу
     * @param node - дочерний узел
     */
    public void addChildren(Node node){
        node.setParent(this);
        children.add(node);
    }

    /**
     * Метод добавления коллекции узлов к текущему узлу
     * @param children - коллекция узлов
     */
    public void addChildren(Collection<Node> children){
        this.children=children;
        for (Node node:children) {
            node.setParent(this);
        }
    }

    /**
     * Метод получения дочерних узлов текущего узла
     * @return - дочерние узлы текущего узла
     */
    public Collection<Node> getChildren(){
        return children;
    }

    /**
     * Метод получения id текущего узла
     * @return id текущего узла
     */
    public int getId(){
        return id;
    }

    /**
     * метод установки id для текущей вершины
     * @param idNode - id для установки
     */
    public void setId(int idNode){
        this.id =idNode;
    }

    /**
     * получение значения текщего узла
     * @return - значение текущего узла
     */
    public T getValue() {
        return value;
    }

    /**
     * установка значения для текущего узла
     * @param value - значение для текщего узла
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Метод получения родительского узла для текщего узла
     * @return - родительский узел
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Метод установки родительского узла для текущего узла
     * @param parent - родительский узел
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }
    /**
     *Метод клонирование узла
     * @return клонированное дерево
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
