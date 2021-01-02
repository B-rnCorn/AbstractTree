package com.project.abstract_tree;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeTest {

    @Test
    public void getRoot() {
        Node expectedRootNode = new Node(0,"root");
        Tree tree=new Tree(expectedRootNode);
        Assert.assertEquals(expectedRootNode,tree.getRoot());
    }

    @Test
    public void setRoot() {
        Node expectedRootNode = new Node(0,"root");
        Tree tree=new Tree();
        tree.setRoot(expectedRootNode);
        Assert.assertEquals(expectedRootNode,tree.getRoot());
    }

    @Test
    public void deleteTree() {
        Tree tree = new Tree();
        tree.deleteTree();
        Assert.assertEquals(null,tree.getRoot());
    }

    @Test
    public void add() {
        Tree expectedTree = null;
        Tree actualTree = new Tree(new Node<String>(0, "Корень"));
        actualTree.add(0,  new Node<String>(1,  "Ветвь 1"));
        actualTree.add(0,  new Node<String>(10, "Ветвь 2"));
        try {
            expectedTree.deserialization("src/test/java/com/project/abstract_tree/test_add.json");
        }catch (TreeException e){
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(expectedTree,actualTree);
    }

    @Test
    public void removeNodeById() {

    }

    @Test
    public void splitById() {

    }

    @Test
    public void search() {

    }

    @Test
    public void serialization() {
        Tree expectedTree = new Tree(new Node<String>(0, "Корень"));
        expectedTree.add(0,  new Node<String>(1,  "Ветвь 1"));
        expectedTree.add(0,  new Node<String>(10, "Ветвь 2"));
        expectedTree.add(1,  new Node<String>(6,  "Ветвь 3"));
        expectedTree.add(1,  new Node<String>(4,  "Ветвь 4"));
        expectedTree.add(10, new Node<String>(11, "Ветвь 5"));
        expectedTree.add(10, new Node<String>(20, "Ветвь 6"));
        expectedTree.add(10, new Node<String>(12, "Ветвь 7"));
        expectedTree.add(11, new Node<String>(15, "Ветвь 8"));
        expectedTree.add(11, new Node<String>(30, "Ветвь 9"));
        expectedTree.add(15, new Node<String>(50, "Ветвь 10"));
        Tree actualTree=null;
        try {
            expectedTree.serialization("src/test/java/com/project/abstract_tree/test_serialization.json");
            actualTree.deserialization("src/test/java/com/project/abstract_tree/test_serialization.json");
        }catch (TreeException e)
        {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(expectedTree,actualTree);
    }

    @Test
    public void deserialization() {
        Tree expectedTree = new Tree(new Node<String>(0, "Корень"));
        expectedTree.add(0,  new Node<String>(1,  "Ветвь 1"));
        expectedTree.add(0,  new Node<String>(10, "Ветвь 2"));
        expectedTree.add(1,  new Node<String>(6,  "Ветвь 3"));
        expectedTree.add(1,  new Node<String>(4,  "Ветвь 4"));
        expectedTree.add(10, new Node<String>(11, "Ветвь 5"));
        expectedTree.add(10, new Node<String>(20, "Ветвь 6"));
        expectedTree.add(10, new Node<String>(12, "Ветвь 7"));
        expectedTree.add(11, new Node<String>(15, "Ветвь 8"));
        expectedTree.add(11, new Node<String>(30, "Ветвь 9"));
        expectedTree.add(15, new Node<String>(50, "Ветвь 10"));
        Tree actualTree=null;
        try {
            actualTree.deserialization("src/test/java/com/project/abstract_tree/test_serialization.json");
        }catch (TreeException e){
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(expectedTree,actualTree);
    }

    @Test
    public void addBranch() {
    }

    @Test
    public void deleteBranch() {
    }

    @Test
    public void testClone() {
    }
}