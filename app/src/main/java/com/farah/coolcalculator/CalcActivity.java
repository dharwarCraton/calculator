package com.farah.coolcalculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.sql.Array;

public class CalcActivity extends Activity {

    String value = "";
    TextView results_textView;

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

        Button[] calculatorButtonsWithListeners = new Button[11];
        calculatorButtonsWithListeners[0] = button_0;
        calculatorButtonsWithListeners[1] = button_1;
        calculatorButtonsWithListeners[2] = button_2;
        calculatorButtonsWithListeners[3] = button_3;
        calculatorButtonsWithListeners[4] = button_4;
        calculatorButtonsWithListeners[5] = button_5;
        calculatorButtonsWithListeners[6] = button_6;
        calculatorButtonsWithListeners[7] = button_7;
        calculatorButtonsWithListeners[8] = button_8;
        calculatorButtonsWithListeners[9] = button_9;
        calculatorButtonsWithListeners[10] = button_clear;

        ImageButton[] calculatorImageButtonsWithListeners = new ImageButton[5];
        calculatorImageButtonsWithListeners[0] = button_EqualTo;
        calculatorImageButtonsWithListeners[1] = button_divide;
        calculatorImageButtonsWithListeners[2] = button_add;
        calculatorImageButtonsWithListeners[3] = button_multiply;
        calculatorImageButtonsWithListeners[4] = button_subtract;

        setListenerOnButtons(calculatorButtonsWithListeners);
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
        for (ImageButton imageButton : imageButtons) {
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do something
                }
            });
        }
    }

    private void valueEntered(int buttonPressed) {
        value += String.valueOf(buttonPressed);
        results_textView.setText(value);
    }
}
