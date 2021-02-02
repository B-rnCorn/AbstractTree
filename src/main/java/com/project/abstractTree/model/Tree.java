package com.project.abstractTree.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.abstractTree.exceptions.ErrorMessage;
import com.project.abstractTree.exceptions.TreeException;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;

/**
 * Abstract Tree Class
 *
 * @author Sergey
 * @author Anrey
 * @version 1.0.0
 */
public class Tree<T> implements Serializable, Cloneable {
    /**
     * Field for tree root
     */
    private Node<T> root;

    /**
     * Constructor
     *
     * @param root - root node
     */
    public Tree(Node<T> root) {
        this.root = root;
    }

    /**
     * Default constructor
     *
     * @see Tree#Tree(Node)
     */
    public Tree() {
        this(new Node<T>());
    }

    /**
     * Method for getting root
     *
     * @return root node
     */
    public Node<T> getRoot() {
        return root;
    }

    /**
     * Method for setting root
     *
     * @param root - root node
     */
    public void setRoot(Node<T> root) {
        this.root = root;
    }

    /**
     * Method for deleting Tree
     */
    public void delete() {
        this.root = null;
    }

    /**
     * Method for adding node
     *
     * @param id         - parent id
     * @param addingNode - node for adding
     * @return - true if added, false - if not added
     */
    public boolean add(int id, Node<T> addingNode) {
        Node<T> parent = search(id);
        if (parent.getId() <= addingNode.getId()) {
            addingNode.setParent(parent);
            parent.addChildren(addingNode);
            return true;
        } else return false;
    }

    /**
     * Remove node
     *
     * @param id - id node for removing
     */
    public void removeNodeById(int id) {
        Node<T> parent = search(root, id).getParent();
        removeSubNode(id, parent);
    }

    /**
     * Removing node from the parent's collection of child nodes
     *
     * @param subNodeId - id of removing node
     * @param node      - parent node
     */
    public boolean removeSubNode(int subNodeId, Node<T> node) {
        Iterator<Node<T>> iterator = node.getChildren().iterator();

        while (iterator.hasNext()) {
            Node<T> tmp = iterator.next();
            if (tmp.getId() == subNodeId) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Splitting tree branch
     *
     * @param id - id of node for slitting
     */
    public void splitById(int id) {
        Node<T> splittingNode = search(id);
        int parentId = splittingNode.getParent().getId();
        Collection<Node<T>> collection = splittingNode.getChildren();
        for (Node<T> child : collection) {
            add(parentId, child);
        }
        removeNodeById(id);
    }

    /**
     * Search node by id
     *
     * @param id - id node
     * @return node with given id or null if node with same id not exist
     */
    public Node<T> search(int id) {
        Node<T> res = root;
        res = search(res, id);
        if (res.getId() == id) return res;
        else return null;
    }

    private Node<T> search(Node<T> tmp, int nodeId) {
        Node<T> result = tmp;
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
     *
     * @param writer - writer for writing tree to receiver
     */
    public void write(Writer writer) throws TreeException {
        try {
            writer.write(this.toString());
        } catch (IOException e) {
            throw new TreeException(ErrorMessage.O_ERROR_MESSAGE, e);
        }
    }

    /**
     * Method for reading tree with using Reader
     *
     * @param reader - reader for reading tree from source
     */
    public void read(Reader reader) throws TreeException {
        Tree<T> tree;
        try {
            ObjectMapper mapper = new ObjectMapper();
            tree = mapper.readValue(reader, this.getClass());
        } catch (IOException e) {
            throw new TreeException(ErrorMessage.I_ERROR_MESSAGE, e);
        }
        this.root = tree.getRoot();
    }

    /**
     * Method for adding node/branch by id
     *
     * @param id      - id destination node for adding node/branch
     * @param nodeAdd - node/top node of branch for adding
     */
    public void addBranch(int id, Node<T> nodeAdd) throws TreeException {
        Node<T> destinationNode = search(id).clone();
        if (add(id, nodeAdd)) {
            if (destinationNode.getChildren() != null) {
                Collection<Node<T>> children = destinationNode.getChildren();
                for (Node<T> child : children) {
                    addBranch(nodeAdd.getId(), child);
                }
            }
        } else throw new TreeException(ErrorMessage.ADDING_BRANCH_ERROR);
    }

    /**
     * Method for delete node/branch by id
     *
     * @param id - id node for removing
     */
    public void deleteBranch(int id) {
        Node<T> delNode = search(id);
        Node<T> parent = delNode.getParent();
        if (delNode.getParent() != null) {
            Collection<Node<T>> children = parent.getChildren();
            for (Node<T> child : children) {
                if (child.getId() == id) child = null;
            }
        }
    }

    /**
     * Method for clone Tree
     *
     * @return cloned Tree or null if clone not supported
     */
    public Tree<T> clone() {
        Node<T> clonedRoot = this.root.clone();
        clone(this.root, this.root.clone());
        return new Tree<T>(clonedRoot);
    }

    /**
     * Method for recursive clone
     *
     * @param sourceNode - cloneable node
     * @param cloneNode  - cloned node
     * @see Tree#clone()
     */
    private void clone(Node<T> sourceNode, Node<T> cloneNode) {
        Node<T> tmp;
        Collection<Node<T>> collection = sourceNode.getChildren();
        for (Node<T> child : collection) {
            tmp = child.clone();
            if (sourceNode.getParent() != null) tmp.setParent(cloneNode);
            cloneNode.addChildren(tmp);
            clone(child, tmp);
        }
    }

    /**
     * Method for converting Tree to String in JSON format
     *
     * @return Tree as String
     */
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
