package com.project.abstract_tree;

public class Main {

    public static void main(String[] args) {
        Tree myTree = new Tree(new Node<String>(0, "Корень"));
        System.out.println(myTree.getRoot().getValue());
        myTree.add(0, new Node<String>(1, "Ветвь 1"));
        myTree.add(0, new Node<String>(10, "Ветвь 2"));
        myTree.add(1, new Node<String>(6, "Ветвь 3"));
        myTree.add(1, new Node<String>(4, "Ветвь 4"));
        myTree.add(10, new Node<String>(20, "Ветвь 5"));
        try {
            myTree.serialization("output.json");
            myTree.setRoot(null);
            myTree.deserialization("output.json");
            Tree TreeSerial=new Tree();
            System.out.println("serialized");
            TreeSerial.deserialization("output.json");
            TreeSerial.outputTree(TreeSerial.getRoot());
        }catch (TreeException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Исходное дерево");
        System.out.println(myTree.getRoot().getValue());
        myTree.outputTree(myTree.getRoot());
        Tree myTree2 = myTree.clone();
        System.out.println("Клонированное дерево");
        myTree2.outputTree(myTree.getRoot());
        /*for (int i = 0; i < 5; i++) {
            myTree.add(i, new Node<String>(1, "Ветвь"+i));
            myTree.add(i, new Node<String>(1, "Ветвь"+i));
        }*/
    }
}
