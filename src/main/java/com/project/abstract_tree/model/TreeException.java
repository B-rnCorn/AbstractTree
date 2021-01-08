package com.project.abstract_tree.model;

/**
 * Исключение, выбрасывающееся при возникновении исключительных ситуаций, свзяанных с деревом
 * Передается сообщение с описанием ошибки, которое потом можно вызвать методом getMessage
 * @author Сергей
 * @version 1.0
 */
public class TreeException extends Exception {
    public TreeException(String message){
        super(message);
    }
}
