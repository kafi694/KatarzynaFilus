package com.example.amicablenumbers.userInterface.base;

/**
 * Interface is used as a base to create presenter classes.
 * Presenter assumes the functionality of the “middle-man”.
 *
 * @author Katarzyna Filus
 * @version 1.0
 */
public interface BaseView {

    /**
     * Function used to set text on a screen.
     */
    void setTextViewValue(String targetValue);
}
