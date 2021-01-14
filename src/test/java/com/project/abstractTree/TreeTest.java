package com.project.abstractTree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TreeTest {
    private Tree<String> expectedTree;

    @Before
    public void init() {
        expectedTree = new Tree<String>(new Node<String>(0, "Корень"));
        expectedTree.add(0, new Node<String>(1, "Ветвь 1"));
        expectedTree.add(0, new Node<String>(10, "Ветвь 2"));
        expectedTree.add(1, new Node<String>(6, "Ветвь 3"));
        expectedTree.add(1, new Node<String>(4, "Ветвь 4"));
        expectedTree.add(10, new Node<String>(11, "Ветвь 5"));
        expectedTree.add(10, new Node<String>(20, "Ветвь 6"));
        expectedTree.add(10, new Node<String>(12, "Ветвь 7"));
        expectedTree.add(11, new Node<String>(15, "Ветвь 8"));
        expectedTree.add(11, new Node<String>(30, "Ветвь 9"));
        expectedTree.add(15, new Node<String>(50, "Ветвь 10"));
    }

    @Before
    public void createTestFile() {
        Tree<String> expectedTree = new Tree<String>(new Node<String>(0, "Корень"));
        expectedTree.add(0, new Node<String>(1, "Ветвь 1"));
        expectedTree.add(1, new Node<String>(6, "Ветвь 3"));
        expectedTree.add(1, new Node<String>(4, "Ветвь 4"));
        expectedTree.add(0, new Node<String>(10, "Ветвь 2"));
        expectedTree.add(6, new Node<String>(8, "Ветвь"));
        expectedTree.add(8, new Node<String>(9, "Ветвь"));
        expectedTree.add(10, new Node<String>(11, "Ветвь 5"));
        expectedTree.add(10, new Node<String>(20, "Ветвь 6"));
        expectedTree.add(10, new Node<String>(12, "Ветвь 7"));
        expectedTree.add(11, new Node<String>(15, "Ветвь 8"));
        expectedTree.add(11, new Node<String>(30, "Ветвь 9"));
        expectedTree.add(15, new Node<String>(50, "Ветвь 10"));
        try {
            FileWriter fileWriter = new FileWriter("src/test/java/com/project/abstractTree/resources/testDeleteBranch.json", false);
            expectedTree.write(fileWriter);
            fileWriter.close();
        } catch (TreeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getRoot() {
        Node<String> expectedRootNode = new Node<String>(0, "Корень");
        Assert.assertEquals(expectedRootNode.getValue(), expectedTree.getRoot().getValue());
        Assert.assertEquals(expectedRootNode.getId(), expectedTree.getRoot().getId());
    }

    @Test
    public void setRoot() {
        Node<String> expectedRootNode = new Node<String>(1, "Корень");
        expectedTree.setRoot(expectedRootNode);
        Assert.assertEquals(expectedRootNode, expectedTree.getRoot());
    }

    @Test
    public void deleteTree() {
        expectedTree.delete();
        Assert.assertNull(expectedTree.getRoot());
    }

    @Test
    public void add() {
        Tree<String> actualTree = new Tree<String>();
        try {
            FileReader fileReader = new FileReader("src/test/java/com/project/abstractTree/resources/testAdd.json");
            actualTree.read(fileReader);
            fileReader.close();
        } catch (TreeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        actualTree.add(15, new Node<String>(50, "Ветвь 10"));
        actualTree.add(10, new Node<String>(20, "Ветвь 6"));
        Assert.assertEquals(expectedTree.getRoot().getId(), actualTree.getRoot().getId());
        Assert.assertEquals(expectedTree.search(50).getValue(), actualTree.search(50).getValue());
        Assert.assertEquals(expectedTree.search(20).getValue(), actualTree.search(20).getValue());
    }

    @Test
    public void removeNodeById() {
        Tree<String> actualTree = new Tree<String>();
        try {
            FileReader fileReader = new FileReader("src/test/java/com/project/abstractTree/resources/testRemoveById.json");
            actualTree.read(fileReader);
            fileReader.close();
        } catch (TreeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        actualTree.removeNodeById(25);
        actualTree.removeNodeById(31);
        Assert.assertEquals(expectedTree.getRoot().getId(), actualTree.getRoot().getId());
        Assert.assertEquals(expectedTree.search(1).getValue(), actualTree.search(1).getValue());
        Assert.assertEquals(expectedTree.search(10).getValue(), actualTree.search(10).getValue());
        Assert.assertEquals(expectedTree.search(6).getValue(), actualTree.search(6).getValue());
        Assert.assertEquals(expectedTree.search(4).getValue(), actualTree.search(4).getValue());
        Assert.assertEquals(expectedTree.search(11).getValue(), actualTree.search(11).getValue());
        Assert.assertEquals(expectedTree.search(20).getValue(), actualTree.search(20).getValue());
        Assert.assertEquals(expectedTree.search(12).getValue(), actualTree.search(12).getValue());
        Assert.assertEquals(expectedTree.search(15).getValue(), actualTree.search(15).getValue());
        Assert.assertEquals(expectedTree.search(30).getValue(), actualTree.search(30).getValue());
        Assert.assertEquals(expectedTree.search(50).getId(), actualTree.search(50).getId());
        Assert.assertNull(actualTree.search(25));
        Assert.assertNull(actualTree.search(31));
    }

    @Test
    public void splitById() {
        Tree<String> actualTree = new Tree<String>();
        try {
            FileReader fileReader = new FileReader("src/test/java/com/project/abstractTree/resources/testSplitById.json");
            actualTree.read(fileReader);
            fileReader.close();
        } catch (TreeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        actualTree.splitById(5);
        Assert.assertEquals(expectedTree.search(1).getValue(), actualTree.search(1).getValue());
        Assert.assertEquals(expectedTree.search(6).getValue(), actualTree.search(6).getValue());
        Assert.assertEquals(expectedTree.search(4).getValue(), actualTree.search(4).getValue());
        Assert.assertEquals(expectedTree.search(11).getValue(), actualTree.search(11).getValue());
        Assert.assertEquals(expectedTree.search(20).getValue(), actualTree.search(20).getValue());
        Assert.assertEquals(expectedTree.search(12).getValue(), actualTree.search(12).getValue());
        Assert.assertEquals(expectedTree.search(15).getValue(), actualTree.search(15).getValue());
        Assert.assertEquals(expectedTree.search(30).getValue(), actualTree.search(30).getValue());
        Assert.assertEquals(expectedTree.search(50).getId(), actualTree.search(50).getId());
        Assert.assertNull(actualTree.search(5));
    }

    @Test
    public void search() {
        Assert.assertNotNull(expectedTree.search(10));
        Assert.assertEquals(expectedTree.search(10).getId(), 10);
    }

    @Test
    public void write() {
        Tree<String> actualTree = new Tree<String>();
        try {
            FileWriter fileWriter = new FileWriter("src/test/java/com/project/abstractTree/resources/testIO.json", false);
            expectedTree.write(fileWriter);
            fileWriter.close();
            FileReader fileReader = new FileReader("src/test/java/com/project/abstractTree/resources/testIO.json");
            actualTree.read(fileReader);
            fileReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (TreeException e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(expectedTree.getRoot().getId(), actualTree.getRoot().getId());
        Assert.assertEquals(expectedTree.search(1).getValue(), actualTree.search(1).getValue());
        Assert.assertEquals(expectedTree.search(10).getValue(), actualTree.search(10).getValue());
        Assert.assertEquals(expectedTree.search(6).getValue(), actualTree.search(6).getValue());
        Assert.assertEquals(expectedTree.search(4).getValue(), actualTree.search(4).getValue());
        Assert.assertEquals(expectedTree.search(11).getValue(), actualTree.search(11).getValue());
        Assert.assertEquals(expectedTree.search(20).getValue(), actualTree.search(20).getValue());
        Assert.assertEquals(expectedTree.search(12).getValue(), actualTree.search(12).getValue());
        Assert.assertEquals(expectedTree.search(15).getValue(), actualTree.search(15).getValue());
        Assert.assertEquals(expectedTree.search(30).getValue(), actualTree.search(30).getValue());
        Assert.assertEquals(expectedTree.search(50).getValue(), actualTree.search(50).getValue());

    }

    @Test
    public void read() {
        Tree<String> actualTree = new Tree<String>();
        try {
            FileReader fileReader = new FileReader("src/test/java/com/project/abstractTree/resources/testIO.json");
            actualTree.read(fileReader);
            fileReader.close();
        } catch (TreeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(expectedTree.getRoot().getId(), actualTree.getRoot().getId());
        Assert.assertEquals(expectedTree.search(1).getValue(), actualTree.search(1).getValue());
        Assert.assertEquals(expectedTree.search(10).getValue(), actualTree.search(10).getValue());
        Assert.assertEquals(expectedTree.search(6).getValue(), actualTree.search(6).getValue());
        Assert.assertEquals(expectedTree.search(4).getValue(), actualTree.search(4).getValue());
        Assert.assertEquals(expectedTree.search(11).getValue(), actualTree.search(11).getValue());
        Assert.assertEquals(expectedTree.search(20).getValue(), actualTree.search(20).getValue());
        Assert.assertEquals(expectedTree.search(12).getValue(), actualTree.search(12).getValue());
        Assert.assertEquals(expectedTree.search(15).getValue(), actualTree.search(15).getValue());
        Assert.assertEquals(expectedTree.search(30).getValue(), actualTree.search(30).getValue());
        Assert.assertEquals(expectedTree.search(50).getValue(), actualTree.search(50).getValue());
    }

    @Test
    public void addBranch() {
        Tree<String> actualTree = new Tree<String>();
        try {
            FileReader fileReader = new FileReader("src/test/java/com/project/abstractTree/resources/testAddBranch.json");
            actualTree.read(fileReader);
            fileReader.close();
        } catch (TreeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Tree<String> addingNodes = new Tree<String>();
        addingNodes.setRoot(new Node<String>(1,"Ветвь 1"));
        addingNodes.add(1,new Node<String>(6,"Ветвь 3"));
        addingNodes.add(1,new Node<String>(4,"Ветвь 4"));
        try {
            actualTree.addBranch(0, addingNodes.getRoot());
        }catch (TreeException e){
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(expectedTree.getRoot().getId(), actualTree.getRoot().getId());
        Assert.assertEquals(expectedTree.search(1).getValue(), actualTree.search(1).getValue());
        Assert.assertEquals(expectedTree.search(10).getValue(), actualTree.search(10).getValue());
        Assert.assertEquals(expectedTree.search(6).getValue(), actualTree.search(6).getValue());
        Assert.assertEquals(expectedTree.search(4).getValue(), actualTree.search(4).getValue());
        Assert.assertEquals(expectedTree.search(11).getValue(), actualTree.search(11).getValue());
        Assert.assertEquals(expectedTree.search(20).getValue(), actualTree.search(20).getValue());
        Assert.assertEquals(expectedTree.search(12).getValue(), actualTree.search(12).getValue());
        Assert.assertEquals(expectedTree.search(15).getValue(), actualTree.search(15).getValue());
        Assert.assertEquals(expectedTree.search(30).getValue(), actualTree.search(30).getValue());
        Assert.assertEquals(expectedTree.search(50).getValue(), actualTree.search(50).getValue());
    }

    @Test
    public void deleteBranch() {
        Tree<String> actualTree = new Tree<String>();
        try {
            FileReader fileReader = new FileReader("src/test/java/com/project/abstractTree/resources/testDeleteBranch.json");
            actualTree.read(fileReader);
            fileReader.close();
        } catch (TreeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        actualTree.deleteBranch(8);
        Assert.assertEquals(expectedTree.getRoot().getId(), actualTree.getRoot().getId());
        Assert.assertEquals(expectedTree.search(1).getValue(), actualTree.search(1).getValue());
        Assert.assertEquals(expectedTree.search(10).getValue(), actualTree.search(10).getValue());
        Assert.assertEquals(expectedTree.search(6).getValue(), actualTree.search(6).getValue());
        Assert.assertEquals(expectedTree.search(4).getValue(), actualTree.search(4).getValue());
        Assert.assertEquals(expectedTree.search(11).getValue(), actualTree.search(11).getValue());
        Assert.assertEquals(expectedTree.search(20).getValue(), actualTree.search(20).getValue());
        Assert.assertEquals(expectedTree.search(12).getValue(), actualTree.search(12).getValue());
        Assert.assertEquals(expectedTree.search(15).getValue(), actualTree.search(15).getValue());
        Assert.assertEquals(expectedTree.search(30).getValue(), actualTree.search(30).getValue());
        Assert.assertEquals(expectedTree.search(50).getValue(), actualTree.search(50).getValue());
    }

    @Test
    public void testClone() {
        Tree<String> actualTree = expectedTree.clone();
        Assert.assertEquals(expectedTree.getRoot().getId(), actualTree.getRoot().getId());
        Assert.assertEquals(expectedTree.search(1).getValue(), actualTree.search(1).getValue());
        Assert.assertEquals(expectedTree.search(10).getValue(), actualTree.search(10).getValue());
        Assert.assertEquals(expectedTree.search(6).getValue(), actualTree.search(6).getValue());
        Assert.assertEquals(expectedTree.search(4).getValue(), actualTree.search(4).getValue());
        Assert.assertEquals(expectedTree.search(11).getValue(), actualTree.search(11).getValue());
        Assert.assertEquals(expectedTree.search(20).getValue(), actualTree.search(20).getValue());
        Assert.assertEquals(expectedTree.search(12).getValue(), actualTree.search(12).getValue());
        Assert.assertEquals(expectedTree.search(15).getValue(), actualTree.search(15).getValue());
        Assert.assertEquals(expectedTree.search(30).getValue(), actualTree.search(30).getValue());
        Assert.assertEquals(expectedTree.search(50).getValue(), actualTree.search(50).getValue());
        Assert.assertNotEquals(expectedTree, actualTree);
    }
}