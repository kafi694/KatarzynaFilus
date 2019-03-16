package com.example.amicablenumbers.userInterface.screens.main;

import android.content.Intent;

import com.example.amicablenumbers.model.AmicableModel;
import com.example.amicablenumbers.model.WrongParametersException;
import com.example.amicablenumbers.userInterface.screens.second.SecondActivity;

/**
 * Class is used as a presenter for main activity.
 * Presenter assumes the functionality of the “middle-man”.
 *
 * @author Katarzyna Filus
 * @version 1.0
 */
public class MainPresenter implements MainContract.Presenter {

    /**
     * Instance of view.
     */
    private MainContract.View view;
    /**
     * Instance of model.
     */
    private AmicableModel model;

    /**
     * Constructor of the class.
     */
    MainPresenter(MainContract.View view) {
        this.view = view;
        model = new AmicableModel();
    }

    /**
     * Starting new screen. Here, not needed - everything set in xml file.
     */
    @Override
    public void start() { }

    /**
     * Resuming screen. Here, not needed - screen is not resumed.
     */
    @Override
    public void resume() { }

    /**
     * Function responsible for handling check button click. It calculates results (giving data to model and back).
     * Catches model and format exceptions. Sends results to intent when everything was fine.
     */
    @Override
    public void onCheckButtonClick() {

        String result;
        try {
            int a = Integer.parseInt(view.getFirstNumberTextViewValue());
            int b = Integer.parseInt(view.getSecondNumberTextViewValue());
            result = model.checkNumbers(a,b);
        } catch (NumberFormatException e) {
            view.showToast("You gave wrong input!!!");
            return;
        } catch (WrongParametersException e) {
            view.showToast(e.getMessage());
            return;
        }
        Intent intent = view.createNewIntent(SecondActivity.class);
        intent.putExtra("result", result);
        view.startNewActivity(intent);
    }

    /**
     * Function responsible for handling generate button click. It calculates results (giving data to model and back).
     * Catches model and format exceptions. Sends results to intent when everything was fine.
     */
    @Override
    public void onGenerateButtonClicked() {

        String result;
        try {
            int c = Integer.parseInt(view.getEndpointTextViewValue());
            result = model.generate(c);
        } catch (NumberFormatException e) {
            view.showToast("Wrong input!");
            return;
        } catch (WrongParametersException e) {
            view.showToast(e.getMessage());
            return;
        }
        Intent intent = view.createNewIntent(SecondActivity.class);
        intent.putExtra("result", result);
        view.startNewActivity(intent);
    }

}