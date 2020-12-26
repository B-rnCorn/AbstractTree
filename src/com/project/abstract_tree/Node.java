package com.project.abstract_tree;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class Node <T>{
    private int id;
    private T value;
    private Collection<Node> children;
    private Node parent;
    private int numChildren=0;
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
        this.children = new Collection<Node>() {
            @Override
            public int size() {
                return numChildren;
            }

            @Override
            public boolean isEmpty() {
                return size()==0;
            }

            @Override
            public boolean contains(Object o) {
                return Objects.equals(o, this);
            }

            @Override
            public Iterator<Node> iterator() {
                return new Iterator<Node>() {
                    @Override
                    public boolean hasNext() {
                        return next()!=null;
                    }

                    @Override
                    public Node next() {
                        return null;//Я не знаю как обратиться к следующему элементу в общем для колекции
                    }
                };
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Node node) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Node> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
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
        numChildren++;
    }

    /**
     * Метод добавления дочернего узла к текущему узлу
     * @param node - дочерний узел
     */
    public void addChildren(Node node){
        node.setParent(this);
        children.add(node);
        numChildren++;
    }

    /**
     * Метод добавления коллекции узлов к текущему узлу
     * @param children - коллекция узлов
     */
    public void addChildren(Collection<Node> children){
        numChildren+=children.size();
        this.children=children;
        for (Node node:children) {
            node.setParent(this);
        }
        numChildren+=children.size();
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
        Node cloned= new Node (this.id,this.value,this.parent);
        cloned.addChildren(this.children);
        return cloned;
    }
}
