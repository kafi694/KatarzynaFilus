/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Class of an Exception. It is thrown when numbers are too small to be amicable.
 *
 * @author Katarzyna Filus
 * @version 1.0
 */
public class WrongParametersException extends Exception {
/**
 * Constructor with no arguments.
 */
    WrongParametersException() {
    }
/**
 * Constructor with String argument. Thanks to it we can throw it with a comment.
 */
    WrongParametersException(String message) {
        super(message);
    }

}
