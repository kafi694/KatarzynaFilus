package com.example.amicablenumbers.model;

/**
 * Class of an Exception. It is thrown when numbers are too small to be
 * amicable or upper endpoint of the chosen interval is less or equals zero.
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
     * Constructor with String argument. Thanks to it we can throw it with a
     * comment.
     */
    WrongParametersException(String message) {
        super(message);
    }

}
