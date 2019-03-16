package com.example.amicablenumbers.userInterface.screens.second;

import android.content.Intent;

/**
 * Class is used as a presenter for second activity.
 * Presenter assumes the functionality of the “middle-man”.
 *
 * @author Katarzyna Filus
 * @version 1.0
 */
public class SecondPresenter implements SecondContract.Presenter {

    /**
     * Instance of view.
     */
    private SecondContract.View view;

    /**
     * Constructor of the class.
     */
    SecondPresenter(SecondContract.View view) {
        this.view = view;
    }

    /**
     * Function used when the screen starts. It takes results from intent (it was set by putExtra).
     */
    @Override
    public void start() {
        Intent intent = view.getStartIntent();
        String message = intent.getStringExtra("result");
        view.setTextViewValue(message);
    }

    /**
     * Function used when the screen resumes.
     */
    @Override
    public void resume() {

    }
}