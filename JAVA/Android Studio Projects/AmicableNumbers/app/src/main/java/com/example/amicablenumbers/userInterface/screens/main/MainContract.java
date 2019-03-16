package com.example.amicablenumbers.userInterface.screens.main;

import android.content.Intent;

import com.example.amicablenumbers.userInterface.base.BasePresenter;
import com.example.amicablenumbers.userInterface.base.BaseView;

/**
 * Contract class for the main activity. Consists of 2 interfaces Iview and presenter).
 *
 * @author Katarzyna Filus
 * @version 1.0
 */
interface MainContract {

    /**
     * Interface modelling the view of an application. It contains some base functions that must be implemented later.
     */
    interface View extends BaseView {
        Intent createNewIntent(Class cls);
        void startNewActivity(Intent intent);
        String getFirstNumberTextViewValue();
        String getSecondNumberTextViewValue();
        String getEndpointTextViewValue();
        void showToast(String message);
    }

    /**
     * Interface modelling the presenter of an application. It contains some base functions that must be implemented later.
     */
    interface Presenter extends BasePresenter {
        void onCheckButtonClick();
        void onGenerateButtonClicked();
    }

}
