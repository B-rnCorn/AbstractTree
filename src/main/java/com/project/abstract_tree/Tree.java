package main.java.com.project.abstract_tree;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collection;

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
        Node parent = search(parentID);
        if(parent.getId()<=addingNode.getId()) {
            addingNode.setParent(parent);
            parent.addChildren(addingNode);
            return true;
        }else return false;
    }
    /**
     * Удаление Узла
     * @param nodeID - id узла для удаления
     */
    public void removeNodeById(int nodeID){
        Node parent = search(root, nodeID).getParent();
        removeSubNode(nodeID, parent);
    }

    /**
     * Удаление дочернего узла
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
     * Расщепление ветви дерева
     * @param id - id узла для расщепления
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
     * Поиск узла по ключу
     * @param id - id узла
     * @return Узел с данным id или null если такого узда нет
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
     * Метод для сериализации дерева в JSON
     * @param fileName - имя файла для сохранения
     */
    public void serialization (String fileName)throws TreeException{
        try {
            FileWriter fileWriter = new FileWriter(new File(fileName),false);
            ObjectMapper mapper=new ObjectMapper();
            mapper.writeValue(fileWriter,this);
            fileWriter.close();
        }catch (IOException e){
            throw new TreeException("Не удалось записать в файл: "+ fileName);
        }
    }
    /*
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
    }*/
    /**
     * Метод для загрузки дерева с файла
     * @param fileName - имя файла для загрузки
     * @return - дерево, считаннок из файла
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
        this.root=tree.root;
    }
    /*
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
    }*/

    /**
     * Метод добавления узла/ветви дерева по id
     * @param nodeId - id узла, в который добавляется ущел/ветвь
     * @param nodeAdd - узел/ветвь для добавления
     */
    //Добавление узла/ветви дерева
    public void addBranch(int nodeId,Node nodeAdd){
        Node destinationNode=search(nodeId);
        destinationNode.addChildren(nodeAdd);
        nodeAdd.setParent(destinationNode);
    }

    /**
     * Метод удаления узла по id
     * @param nodeId - di узла, который нужно удалить
     */
    //Удаление узла/ветви дерева
    public void deleteBranch(int nodeId){
        Node delNode=search(nodeId);
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