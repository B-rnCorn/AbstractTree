package com.project.abstractTree.exceptions;

/**
 * Exception thrown when tree-related exceptions occur
 * A message is sent with a description of the error, which can then be called by the getMessage method
 *
 * @author Sergey
 * @version 1.0.0
 */
public class TreeException extends Exception {
    /**
     * Constructor of exception
     *
     * @param message - message with describing of exception
     * @param cause   - cause of throwing this exception
     */
    public TreeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor of exception
     *
     * @param message - message with describing of exception
     */
    public TreeException(String message) {
        super(message);
    }
}
