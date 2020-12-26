package com.project.abstract_tree;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Tree myTree = new Tree(new Node<String>(0, "Корень"));
        System.out.println(myTree.getRoot().getValue());
        myTree.add(0, new Node<String>(1, "Ветвь 1"));
        myTree.add(0, new Node<String>(10, "Ветвь 2"));
        myTree.add(1, new Node<String>(6, "Ветвь 3"));
        myTree.add(1, new Node<String>(4, "Ветвь 4"));
        myTree.add(10, new Node<String>(5, "Ветвь 5"));
        System.out.println("Исходное дерево");
        System.out.println(myTree.getRoot().getValue());
        myTree.outputTree(myTree.getRoot());
        Tree myTree2 = myTree.clone();
        System.out.println("Клонированное дерево");
        myTree2.outputTree(myTree.getRoot());
        System.out.println("Упорядоченное дерево");
        myTree2.outputTree(myTree.getRoot());
        /*for (int i = 0; i < 5; i++) {
            myTree.add(i, new Node<String>(1, "Ветвь"+i));
            myTree.add(i, new Node<String>(1, "Ветвь"+i));
        }*/
    }
}
