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
     *Конструкторы
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

    public Node(int id, T value){
        this(id, value, null);
    }

    public Node(){
        this(0, (T) new Object());
    }
     /**
     *Геттреры и сеттеры атрибутов
     */
    public void setChildren(int idNode,T value,Node parent){
        children.add(new Node(idNode,value,parent));
    }

    public void setChildren(Node node){
        node.setParent(this);
        children.add(node);
        numChildren++;
    }
    public void setChildren(Collection<Node> children){
        numChildren+=children.size();
        this.children=children;
        for (Node node:children) {
            node.setParent(this);
        }
    }

    public Collection<Node> getChildren(){
        return children;
    }

    public int getId(){
        return id;
    }

    public void setId(int idNode){
        this.id =idNode;
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
    /**
     *Клонирование
     */
    public Node clone(){
        Node cloned= new Node (this.id,this.value,this.parent);
        cloned.setChildren(this.children);
        return cloned;
    }
}
