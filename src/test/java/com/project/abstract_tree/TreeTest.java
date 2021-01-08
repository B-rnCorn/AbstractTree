package com.project.abstract_tree;

import com.project.abstract_tree.model.Node;
import com.project.abstract_tree.model.Tree;
import com.project.abstract_tree.model.TreeException;
import org.junit.Assert;
import org.junit.Test;

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
        Tree expectedTree =new Tree();
        Tree actualTree = new Tree(new Node<String>(0, "Корень"));
        actualTree.add(0,  new Node<String>(1,  "Ветвь 1"));
        actualTree.add(0,  new Node<String>(10, "Ветвь 2"));
        try {
            expectedTree.deserialization("src/test/java/com/project/abstract_tree/test_add.json");
        }catch (TreeException e){
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(expectedTree.getRoot().getId(),actualTree.getRoot().getId());
        Assert.assertEquals(expectedTree.search(1).getValue(),actualTree.search(1).getValue());
        Assert.assertEquals(expectedTree.search(10).getValue(),actualTree.search(10).getValue());
    }

    @Test
    public void removeNodeById() {

    }

    @Test
    public void splitById() {

    }

    @Test
    public void search() {
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
        Assert.assertNotNull(expectedTree.search(10));
        Assert.assertEquals(expectedTree.search(10).getValue(),"Ветвь 2");
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
        Tree actualTree=new Tree();
        try {
            expectedTree.serialization("src/test/java/com/project/abstract_tree/test_serialization.json");
            actualTree.deserialization("src/test/java/com/project/abstract_tree/test_serialization.json");
        }catch (TreeException e)
        {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(expectedTree.getRoot().getId(),actualTree.getRoot().getId());
        Assert.assertEquals(expectedTree.search(1).getValue(),actualTree.search(1).getValue());
        Assert.assertEquals(expectedTree.search(10).getValue(),actualTree.search(10).getValue());
        Assert.assertEquals(expectedTree.search(6).getValue(),actualTree.search(6).getValue());
        Assert.assertEquals(expectedTree.search(4).getValue(),actualTree.search(4).getValue());
        Assert.assertEquals(expectedTree.search(11).getValue(),actualTree.search(11).getValue());
        Assert.assertEquals(expectedTree.search(20).getValue(),actualTree.search(20).getValue());
        Assert.assertEquals(expectedTree.search(12).getValue(),actualTree.search(12).getValue());
        Assert.assertEquals(expectedTree.search(15).getValue(),actualTree.search(15).getValue());
        Assert.assertEquals(expectedTree.search(30).getValue(),actualTree.search(30).getValue());
        Assert.assertEquals(expectedTree.search(50).getValue(),actualTree.search(50).getValue());

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
        Tree actualTree=new Tree();
        try {
            actualTree.deserialization("src/test/java/com/project/abstract_tree/test_serialization.json");
        }catch (TreeException e){
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(expectedTree.getRoot().getId(),actualTree.getRoot().getId());
        Assert.assertEquals(expectedTree.search(1).getValue(),actualTree.search(1).getValue());
        Assert.assertEquals(expectedTree.search(10).getValue(),actualTree.search(10).getValue());
        Assert.assertEquals(expectedTree.search(6).getValue(),actualTree.search(6).getValue());
        Assert.assertEquals(expectedTree.search(4).getValue(),actualTree.search(4).getValue());
        Assert.assertEquals(expectedTree.search(11).getValue(),actualTree.search(11).getValue());
        Assert.assertEquals(expectedTree.search(20).getValue(),actualTree.search(20).getValue());
        Assert.assertEquals(expectedTree.search(12).getValue(),actualTree.search(12).getValue());
        Assert.assertEquals(expectedTree.search(15).getValue(),actualTree.search(15).getValue());
        Assert.assertEquals(expectedTree.search(30).getValue(),actualTree.search(30).getValue());
        Assert.assertEquals(expectedTree.search(50).getValue(),actualTree.search(50).getValue());
    }

    @Test
    public void addBranch() {

    }

    @Test
    public void deleteBranch() {
    }

    @Test
    public void testClone() {
        Tree sourceTree = new Tree(new Node<String>(0, "Корень"));
        sourceTree.add(0,  new Node<String>(1,  "Ветвь 1"));
        sourceTree.add(0,  new Node<String>(10, "Ветвь 2"));
        sourceTree.add(1,  new Node<String>(6,  "Ветвь 3"));
        sourceTree.add(1,  new Node<String>(4,  "Ветвь 4"));
        sourceTree.add(10, new Node<String>(11, "Ветвь 5"));
        sourceTree.add(10, new Node<String>(20, "Ветвь 6"));
        sourceTree.add(10, new Node<String>(12, "Ветвь 7"));
        sourceTree.add(11, new Node<String>(15, "Ветвь 8"));
        sourceTree.add(11, new Node<String>(30, "Ветвь 9"));
        sourceTree.add(15, new Node<String>(50, "Ветвь 10"));
        Tree actualTree=sourceTree.clone();
        Tree expectedTree=new Tree();
        try {
            expectedTree.deserialization("src/test/java/com/project/abstract_tree/test_clone.json");
        }catch (TreeException e){
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(expectedTree.getRoot().getId(),actualTree.getRoot().getId());
        Assert.assertEquals(expectedTree.search(1).getValue(),actualTree.search(1).getValue());
        Assert.assertEquals(expectedTree.search(10).getValue(),actualTree.search(10).getValue());
        Assert.assertEquals(expectedTree.search(6).getValue(),actualTree.search(6).getValue());
        Assert.assertEquals(expectedTree.search(4).getValue(),actualTree.search(4).getValue());
        Assert.assertEquals(expectedTree.search(11).getValue(),actualTree.search(11).getValue());
        Assert.assertEquals(expectedTree.search(20).getValue(),actualTree.search(20).getValue());
        Assert.assertEquals(expectedTree.search(12).getValue(),actualTree.search(12).getValue());
        Assert.assertEquals(expectedTree.search(15).getValue(),actualTree.search(15).getValue());
        Assert.assertEquals(expectedTree.search(30).getValue(),actualTree.search(30).getValue());
        Assert.assertEquals(expectedTree.search(50).getValue(),actualTree.search(50).getValue());
    }
}