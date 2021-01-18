package com.project.abstractTree;

/**
 * Exception thrown when tree-related exceptions occur
 * A message is sent with a description of the error, which can then be called by the getMessage method
 * @author Sergey
 * @version 1.0.0
 */
public class TreeException extends Exception {
    /**
     * Constructor of exception
     * @param message - message with describing of exception
     */
    public TreeException(String message){
        super(message);
    }
}
