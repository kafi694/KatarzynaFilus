package com.example.amicablenumbers.userInterface.base;

/**
 * Interface is used as a base to create presenter classes.
 * Presenter assumes the functionality of the “middle-man”.
 *
 * @author Katarzyna Filus
 * @version 1.0
 */
public interface BasePresenter {

    /**
     * Function used when screen starts.
     */
    void start();

    /**
     * Function used when screen resumes.
     */
    void resume();
}
