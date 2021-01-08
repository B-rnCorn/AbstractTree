package com.project.abstract_tree.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collection;

import java.io.*;
import java.util.Iterator;

/**
 * Abstract Tree Class
 * @author Sergey
 * @author Anrey
 * @version 1.0
 */
public class Tree implements Serializable {
    /**
     * Field for tree root
     */
    private Node root;
     /**
      *Constructor
      * @param root - root node
     */
    public Tree(Node root){
        this.root = root;
    }
    /**
     *Default constructor
     * @see Tree#Tree(Node)
     */
    public Tree(){
        this(new Node());
    }
     /**
     *Method for getting root
       @return root node
     */
    public Node getRoot(){
        return root;
    }
    /**
     *Method for setting root
     * @param root - root node
     */
    public void setRoot(Node root){
        this.root=root;
    }
     /**
     *Method for deleting Tree
     */
    public void deleteTree(){
        this.root = null;
    }

    /**
     * Method for adding node
     * @param id - parent id
     * @param addingNode - node for adding
     * @return - true if added, false - if not added
     */
    public boolean add(int id, Node addingNode){
        Node parent = search(id);
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
        Node parent = search(root, id).getParent();
        removeSubNode(id, parent);
    }

    /**
     * Removing sub node
     * @param subNodeID
     * @param node
     */
    public void removeSubNode(int subNodeID, Node node){
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
     * Splitting tree branch
     * @param id - id of node for slitting
     */
    public void splitById(int id){
        Node splittingNode=search(id);
        int parentId = splittingNode.getParent().getId();

        //Добавить дочерние узлы Разделяемого Узла в список дочерних узлов Родительского Узла
        Collection<Node> collection = splittingNode.getChildren();
        for (Node child : collection){
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
    public Node search(int id){
        Node res=root;
        return res=search(res,id);
    }
    private Node search(Node tmp, int nodeId){
        Node result=tmp;
        if (tmp.getId() == nodeId) {
            return result;
        }
            Collection<Node> list = tmp.getChildren();
            for (Node child : list) {
                result = search(child, nodeId);
                if (result.getId() == nodeId) return result;
            }
        return result;
    }

    /**
     * Method for serialization to JSON
     * @param fileName - name of file for serialization
     */
    public void serialization (String fileName)throws TreeException {
        try {
            FileWriter fileWriter = new FileWriter(new File(fileName),false);
            ObjectMapper mapper=new ObjectMapper();
            mapper.writeValue(fileWriter,this);
            fileWriter.close();
        }catch (IOException e){
            throw new TreeException("Не удалось записать в файл: "+ fileName);
        }
    }
    /**
     * Method for deserialization from JSON
     * @param fileName - name of file for deserialization
     * @return - tree that deserialize from file
     */
    public void deserialization(String fileName)throws TreeException{
        Tree tree=null;
        try {
            FileReader fileReader = new FileReader(new File(fileName));
            ObjectMapper mapper=new ObjectMapper();
            tree=mapper.readValue(fileReader,Tree.class);
        }catch (IOException e){
            throw new TreeException("Не удалось считать файл: "+fileName);
        }
        this.root=tree.getRoot();
    }

    /**
     * Method for adding node/branch by id
     * @param id - id destination node for adding node/branch
     * @param nodeAdd - node/top node of branch for adding
     */
    //Добавление узла/ветви дерева
    public void addBranch(int id,Node nodeAdd){
        Node destinationNode=search(id);
        destinationNode.addChildren(nodeAdd);
        nodeAdd.setParent(destinationNode);
    }

    /**
     * Method for delete node/branch by id
     * @param id - id node for removing
     */
    //Удаление узла/ветви дерева
    public void deleteBranch(int id){
        Node delNode=search(id);
        Collection<Node> parentChildren =delNode.getParent().getChildren();
        Node tempNode;
        int i=0;
        for(Node temp:parentChildren){
            if (temp.getId() == id) parentChildren.remove(i);
            i++;
        }
    }

    /**
     * Method for clone Tree
     * @return cloned Tree
     */
    public Tree clone(){
        Node clonedRoot=this.root.clone();
        clone(this.root,this.root.clone());
        return new Tree(clonedRoot);
    }
    /** Method for recursive clone
     * @see Tree#clone()
     * @param sourceNode - клонируемый узел
     * @param cloneNode - клонированный узел
     * @return  - возвращает клонированное дерево
     */
    private void clone(Node sourceNode,Node cloneNode){
        Node tmp=null;
        Collection<Node> collection = sourceNode.getChildren();
        for (Node child : collection){
            tmp=child.clone();
            if(sourceNode.getParent()!=null) tmp.setParent(cloneNode);
            cloneNode.addChildren(tmp);
            clone(child,tmp);
        }
    }
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