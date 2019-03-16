package com.example.amicablenumbers.userInterface.screens.second;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.amicablenumbers.R;

/**
 * Second Activity - screen with results.
 *
 * @author Katarzyna Filus
 * @version 1.0
 */
public class SecondActivity extends AppCompatActivity implements SecondContract.View {

    /**
     * Instance of presenter.
     */
    private SecondContract.Presenter presenter;

    /**
     * Activity constructor.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        presenter = new SecondPresenter(this);
        presenter.start();
    }

    /**
     * Getting intent.
     */
    @Override
    public Intent getStartIntent() {
        Intent intent = getIntent();
        return intent;
    }

    /**
     * Setting value of a text field with given id.
     */
    @Override
    public void setTextViewValue(String targetValue) {
        ((TextView)findViewById(R.id.result)).setText(targetValue);
    }
}
