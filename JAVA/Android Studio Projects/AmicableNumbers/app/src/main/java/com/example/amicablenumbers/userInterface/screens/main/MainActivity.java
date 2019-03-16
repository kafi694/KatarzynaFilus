package com.example.amicablenumbers.userInterface.screens.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amicablenumbers.R;

/**
 * Main activity of the application - first screen.
 *
 * @author Katarzyna Filus
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity implements MainContract.View {

    /**
     * Instance of presenter.
     */
    private MainContract.Presenter presenter;

    /**
     * Function onCreate sets buttons' listeners and constructs presenter instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button checkButton = findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCheckButtonClicked(v);
            }
        });
        Button generateButton = findViewById(R.id.generateButton);
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGenerateButtonClicked(v);
            }
        });
        presenter = new MainPresenter(this);
        presenter.start();
    }

    /**
     * Function used when the screen is resumed.
     */
    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    /**
     * Function responsible for handling button click (for checking numbers).
     */
    public void onCheckButtonClicked(View view) {

        presenter.onCheckButtonClick();
    }

    /**
     * Function responsible for handling button click (for generating numbers).
     */
    public void onGenerateButtonClicked(View view) {

        presenter.onGenerateButtonClicked();
    }

    /**
     * Function responsible for creating new intent.
     */
    @Override
    public Intent createNewIntent(Class cls) {
        return new Intent(this, cls);
    }

    /**
     * Function responsible for starting new activity.
     */
    @Override
    public void startNewActivity(Intent intent) {
        startActivity(intent);
    }

    /**
     * Getting the text value from the text field with first number (to check 2 numbers).
     */
    @Override
    public String getFirstNumberTextViewValue() {
        return ((EditText)findViewById(R.id.firstNumber)).getText().toString();
    }

    /**
     * Getting the text value from the text field with second number (to check 2 numbers).
     */
    @Override
    public String getSecondNumberTextViewValue() {
        return ((EditText)findViewById(R.id.secondNumber)).getText().toString();
    }

    /**
     * Getting the text value from the text field with endpoint (to generate numbers).
     */
    @Override
    public String getEndpointTextViewValue() {
        return ((EditText)findViewById(R.id.endpoint)).getText().toString();
    }

    /**
     * Function responsible for showing toast on the screen.
     */
    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Setting value of a text field with given id.
     */
    @Override
    public void setTextViewValue(String targetValue) {
        ((TextView)findViewById(R.id.result)).setText(targetValue);
    }
}
