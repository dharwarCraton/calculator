package com.farah.coolcalculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.farah.coolcalculator.utility.Operation;

import java.sql.Array;

public class CalcActivity extends Activity {


    TextView results_textView;


    String value = "";
    String leftValueStr;
    String rightValueStr;
    String results = String.valueOf(0);
    int operator;

    public enum OP {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        EQUAL
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_0 = findViewById(R.id.button_0);
        Button button_1 = findViewById(R.id.button_1);
        Button button_2 = findViewById(R.id.button_2);
        Button button_3 = findViewById(R.id.button_3);
        Button button_4 = findViewById(R.id.button_4);
        Button button_5 = findViewById(R.id.button_5);
        Button button_6 = findViewById(R.id.button_6);
        Button button_7 = findViewById(R.id.button_7);
        Button button_8 = findViewById(R.id.button_8);
        Button button_9 = findViewById(R.id.button_9);

        ImageButton button_EqualTo = findViewById(R.id.button_equalTo);
        ImageButton button_divide = findViewById(R.id.button_divide);
        ImageButton button_add = findViewById(R.id.button_add);
        ImageButton button_multiply = findViewById(R.id.button_multiply);
        ImageButton button_subtract = findViewById(R.id.button_subtract);

        Button button_clear = findViewById(R.id.button_clear);
        results_textView = findViewById(R.id.results_textView);
        results_textView.setText(R.string.initial_resultsText);

        Button[] calculatorButtons = new Button[11];
        calculatorButtons[0] = button_0;
        calculatorButtons[1] = button_1;
        calculatorButtons[2] = button_2;
        calculatorButtons[3] = button_3;
        calculatorButtons[4] = button_4;
        calculatorButtons[5] = button_5;
        calculatorButtons[6] = button_6;
        calculatorButtons[7] = button_7;
        calculatorButtons[8] = button_8;
        calculatorButtons[9] = button_9;
        calculatorButtons[10] = button_clear;

        ImageButton[] calculatorImageButtons = new ImageButton[5];
        calculatorImageButtons[0] = button_EqualTo;
        calculatorImageButtons[1] = button_divide;
        calculatorImageButtons[2] = button_add;
        calculatorImageButtons[3] = button_multiply;
        calculatorImageButtons[4] = button_subtract;

        setListenerOnButtons(calculatorButtons);
        setListenerOnImageButtons(calculatorImageButtons);
    }

    private void setListenerOnButtons(final Button[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            final int buttonValue = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    valueEntered(buttonValue);
                }
            });
        }
    }

    private void setListenerOnImageButtons(ImageButton[] imageButtons) {
        for (int i = 0; i < imageButtons.length; i++) {
            final int buttonId = i;
            imageButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(buttonId) {
                        case 0:
                            operator = Operation.EQUAL_TO;
                            assignLeftValue();
                            results_textView.setText("=");
                            break;
                        case 1:
                            operator = Operation.DIVIDE;
                            assignLeftValue();
                            results_textView.setText("/");
                            break;
                        case 2:
                            operator = Operation.ADD;
                            assignLeftValue();
                            results_textView.setText("+");
                            break;
                        case 3:
                            operator = Operation.MULTIPLY;
                            assignLeftValue();
                            results_textView.setText("x");
                            break;
                        case 4:
                            operator = Operation.SUBTRACT;
                            assignLeftValue();
                            results_textView.setText("-");
                            break;
                    }
                }
            });
        }
    }

    private void valueEntered(int buttonPressed) {
        value += String.valueOf(buttonPressed);
        results_textView.setText(value);
    }

    private void assignLeftValue() {
        if (value != null && (!value.isEmpty())) {
            leftValueStr = value;
            value = "";
        }
    }

    private void performCalculation(Operation operation) {

    }
}
