package com.example.amicablenumbers.userInterface.screens.second;

import android.content.Intent;

import com.example.amicablenumbers.userInterface.base.BasePresenter;
import com.example.amicablenumbers.userInterface.base.BaseView;

/**
 * Contract class for the second activity. Consists of 2 interfaces Iview and presenter).
 *
 * @author Katarzyna Filus
 * @version 1.0
 */
public interface SecondContract {

    /**
     * Interface used as a base to create view.
     */
    interface View extends BaseView {
        Intent getStartIntent();
    }

    /**
     * Interface used as a presenter to create view. Less functionality of a screen - it doesn't have functions.
     */
    interface Presenter extends BasePresenter {

    }
}