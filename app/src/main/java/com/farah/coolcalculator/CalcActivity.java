package com.farah.coolcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.farah.coolcalculator.utility.Operation;

import static com.farah.coolcalculator.utility.Operation.ADD;
import static com.farah.coolcalculator.utility.Operation.DIVIDE;
import static com.farah.coolcalculator.utility.Operation.EQUAL_TO;
import static com.farah.coolcalculator.utility.Operation.MULTIPLY;
import static com.farah.coolcalculator.utility.Operation.SUBTRACT;

public class CalcActivity extends Activity {


    TextView results_textView;


    String numberEntered = "";
    String rightValueStr = "";
    String leftValueStr = "";
    int result = 0;
    @Operation.Operator int currentOperator;

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
                            currentOperator = EQUAL_TO;
                            performCalculation(EQUAL_TO);
                        case 1:
                            currentOperator = DIVIDE;
                            performCalculation(currentOperator);
                            break;
                        case 2:
                            currentOperator = ADD;
                            performCalculation(currentOperator);
                            break;
                        case 3:
                            currentOperator = MULTIPLY;
                            performCalculation(currentOperator);
                            break;
                        case 4:
                            results_textView.setText("-");
                            performCalculation(SUBTRACT);
                            break;
                    }
                }
            });
        }
    }

    private void valueEntered(int buttonPressed) {
        numberEntered += String.valueOf(buttonPressed);
        results_textView.setText(numberEntered);
    }

    private void assignRightValue() {
        if (!numberEntered.isEmpty()) {
            rightValueStr = numberEntered;
            numberEntered = "";
        }
    }

    private void assignLeftValue() {
        if (!numberEntered.isEmpty()) {
            leftValueStr = numberEntered;
            numberEntered = "";
        }
    }

    private void performCalculation(@Operation.Operator int incomingOperator) {
        assignRightValue();

        if (!numberEntered.isEmpty()) {

            int leftValueInt = Integer.parseInt(leftValueStr);
            int rightValueInt = Integer.parseInt(rightValueStr);

            switch (currentOperator) {
                case EQUAL_TO:
                    Log.i(CalcActivity.class.getSimpleName(), String.format("Equals to: %d", result));
                    results_textView.setText(result);
                    break;
                case DIVIDE:
                    results_textView.setText("/");
                    result = leftValueInt / rightValueInt;
                    break;
                case ADD:
                    results_textView.setText("+");
                    result = leftValueInt + rightValueInt;
                    break;
                case MULTIPLY:
                    results_textView.setText("x");
                    result = leftValueInt * rightValueInt;
                    break;
                case SUBTRACT:

                    if (leftValueInt >= rightValueInt) {
                        result = leftValueInt - rightValueInt;
                    } else {
                        result = rightValueInt - leftValueInt;
                    }
                    break;
            }

            leftValueStr = String.valueOf(result);
            results_textView.setText(String.valueOf(result));

        } else {
            assignLeftValue();
        }

        currentOperator = incomingOperator;
    }
}
