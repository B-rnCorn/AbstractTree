package com.project.abstractTree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collection;

import java.io.*;
import java.util.Iterator;

/**
 * Abstract Tree Class
 * @author Sergey
 * @author Anrey
 * @version 1.0.0
 */
public class Tree<T> implements Serializable {
    /**
     * constant for error message in symbol stream input
     */
    final String I_ERROR_MESSAGE="Ошибка ввода с символьного потока ";
    /**
     * constant for error message in symbol stream output
     */
    final String O_ERROR_MESSAGE="Ошибка вывода в символьный поток ";
    /**
     * Field for tree root
     */
    private Node<T> root;
     /**
      *Constructor
      * @param root - root node
     */
    public Tree(Node<T> root){
        this.root = root;
    }
    /**
     *Default constructor
     * @see Tree#Tree(Node)
     */
    public Tree(){
        this(new Node<T>());
    }
     /**
     *Method for getting root
       @return root node
     */
    public Node<T> getRoot(){
        return root;
    }
    /**
     *Method for setting root
     * @param root - root node
     */
    public void setRoot(Node<T> root){
        this.root=root;
    }
     /**
     *Method for deleting Tree
     */
    public void delete(){
        this.root = null;
    }

    /**
     * Method for adding node
     * @param id - parent id
     * @param addingNode - node for adding
     * @return - true if added, false - if not added
     */
    public boolean add(int id, Node<T> addingNode){
        Node<T> parent = search(id);
        if(parent.getId()<=addingNode.getId()) {
            addingNode.setParent(parent);
            parent.addChildren(addingNode);
            return true;
        }else return false;
    }
    /**
     * Remove node
     * @param id - id node for removing
     */
    public void removeNodeById(int id){
        Node<T> parent = search(root, id).getParent();
        removeSubNode(id, parent);
    }

    /**
     * Removing sub node
     * @param subNodeId
     * @param node
     */
    public void removeSubNode(int subNodeId, Node<T> node){
        //Поиск Узла среди дочерних узлов Родителя
        Iterator<Node<T>> iterator = node.getChildren().iterator();

        while(iterator.hasNext()){
            Node<T> tmp = (Node<T>) iterator.next();
            if(tmp.getId() == subNodeId) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Splitting tree branch
     * @param id - id of node for slitting
     */
    public void splitById(int id){
        Node<T> splittingNode=search(id);
        int parentId = splittingNode.getParent().getId();
        //Добавить дочерние узлы Разделяемого Узла в список дочерних узлов Родительского Узла
        Collection<Node<T>> collection = splittingNode.getChildren();
        for (Node<T> child : collection){
            add(parentId, child);
        }
        //Удалить текущий узел из списка дочерних узлов Родителя
        removeSubNode(splittingNode.getId(), splittingNode.getParent());
    }
    /**
     * Search node by id
     * @param id - id node
     * @return node with given id or null if node with same id not exist
     */
    public Node<T> search(int id){
        Node<T> res=root;
        return res=search(res,id);
    }
    private Node<T> search(Node<T> tmp, int nodeId){
        Node<T> result=tmp;
        if (tmp.getId() == nodeId) {
            return result;
        }
            Collection<Node<T>> list = tmp.getChildren();
            for (Node<T> child : list) {
                result = search(child, nodeId);
                if (result.getId() == nodeId) return result;
            }
        return result;
    }
    /**
     * Method for writing tree with using Writer
     * @param writer - writer for writing tree to receiver
     */
    public void write(Writer writer)throws TreeException{
        try {
            writer.write(this.toString());
        }catch (IOException e){
            throw new TreeException(O_ERROR_MESSAGE);
        }
    }
    /**
     * Method for reading tree with using Reader
     * @param reader - reader for reading tree from source
     */
    public void read(Reader reader)throws TreeException{
        Tree<T> tree;
        try {
            ObjectMapper mapper=new ObjectMapper();
            tree=mapper.readValue(reader,Tree.class);
        }catch (IOException e){
            throw new TreeException(I_ERROR_MESSAGE);
        }
        this.root=tree.getRoot();
    }

    /**
     * Method for adding node/branch by id
     * @param id - id destination node for adding node/branch
     * @param nodeAdd - node/top node of branch for adding
     */
    //Добавление узла/ветви дерева
    public void addBranch(int id,Node<T> nodeAdd){
        Node<T> destinationNode=search(id);
        destinationNode.addChildren(nodeAdd);
        nodeAdd.setParent(destinationNode);
    }

    /**
     * Method for delete node/branch by id
     * @param id - id node for removing
     */
    //Удаление узла/ветви дерева
    public void deleteBranch(int id){
        Node<T> delNode=search(id);
        Collection<Node<T>> parentChildren =delNode.getParent().getChildren();
        Node<T> tempNode;
        int i=0;
        for(Node<T> temp:parentChildren){
            if (temp.getId() == id) parentChildren.remove(i);
            i++;
        }
    }

    /**
     * Method for clone Tree
     * @return cloned Tree or null if clone not supported
     */
    public Tree<T> clone(){
        try {
            return new Tree<T>((Node<T>)super.clone());
        }catch (CloneNotSupportedException e){
            return null;
        }
    }

    /**
     * Method for converting Tree to String in JSON format
     * @return Tree as String
     */
    @Override
    public String toString(){
        ObjectMapper mapper=new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        }catch (JsonProcessingException e){
            return null;
        }
    }
    /*
        Node<T> clonedRoot=this.root.clone();
        clone(this.root,this.root.clone());
        return new Tree(clonedRoot);
    }
    /** Method for recursive clone
     * @see Tree#clone()
     * @param sourceNode - клонируемый узел
     * @param cloneNode - клонированный узел
     * @return  - возвращает клонированное дерево
    private void clone(Node<T> sourceNode,Node<T> cloneNode){
        Node<T> tmp=null;
        Collection<Node<T>> collection = sourceNode.getChildren();
        for (Node<T> child : collection){
            tmp=child.clone();
            if(sourceNode.getParent()!=null) tmp.setParent(cloneNode);
            cloneNode.addChildren(tmp);
            clone(child,tmp);
        }
    }*/
    /*
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
    }*/
}