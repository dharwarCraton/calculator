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
    String lhs = "";
    String rhs = "";
    String result = String.valueOf(0);
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

        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lhs = "";
                rhs = "";
                numberEntered = "";
                results_textView.setText(String.valueOf(0));
            }
        });
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
                            processOperation(currentOperator);
                        case 1:
                            currentOperator = DIVIDE;
                            processOperation(currentOperator);
                            break;
                        case 2:
                            currentOperator = ADD;
                            processOperation(currentOperator);
                            break;
                        case 3:
                            currentOperator = MULTIPLY;
                            processOperation(currentOperator);
                            break;
                        case 4:
                            results_textView.setText("-");
                            processOperation(currentOperator);
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

    /*
    Mike notes

    Farah,
    i did the following
    1. wrote some notes above your method thinking through stuff for first time
    2. put a few comments in your method that reference the notes
    3. wrote a stub processOperationExhaustive that contains all cases of the three variables stubbed and findings
        - discovered that many rightValueStr is not needed
        - produced some half code half notes on what might be done
        - thought that i could very well be missing things that need to happen that you might know...

    Hope this helps
    Love,
    Mike


    * @Operation.Operator int is a fancy type, i've never seen this before
    * this one seems easier to follow
    * i was looking at it, does it work completely? didn't run
    * ?
    * what can i do if anything... or did you want me to just look?
    *
    * ok... 5 min pls ... more than 5 minutes... attempting to figure out...
    *
    * when operator is pressed here are the eight possible states
    *       1 !entered !lhs !rhs
    *       2  entered !lhs !rhs
    *       3 !entered  lhs !rhs
    *       4  entered  lhs !rhs
    *       5 !entered !lhs  rhs
    *       6  entered !lhs  rhs
    *       7 !entered  lhs  rhs
    *       8  entered  lhs  rhs
    *
    *       STATE 1 or STATE 2 (FIRST time in processOperation)
    *       it could start with STATE 1 (nothing is entered and someone just presses an operator)
    *           should it treat nothing entered as zero entered and continue as if STATE 2?
    *       or STATE 2 (something is entered but nothing else has happened prior)
    *           when this happens,
    *           lhs = entered
    *           entered = ""
    *       transition to STATE 3
    *
    *       STATE 3 (pushing operator button more than once)
    *       someone has so far typed 123 + and pressed + again to get to STATE 3, what to do
    *       what if they had pressed + before and then pressed - to get here?
    *       seems like you stay in STATE 3 until user enters a number, once they do then you are going to STATE 4
    *
    *       STATE 4 (pushing operator button after putting subsequent number (normal use))
    *       someone has so far typed 123 + 456 and now they press another operator
    *       time to do this?
    *       rhs = entered;
    *       entered = ""
    *       solution = lhs @ rhs;
    *       entered = solution
    *       lhs = ""
    *       rhs = ""
    *       now we're back in STATE 2
    *
    *
    *

     */

    void processOperation(@Operation.Operator int operator) {
        int resultInt = 0;
        @Operation.Operator int incomingOperator = operator;

        // check if first-time number entered is not empty and assign it to lhs
        // empty out numberEntered
        if (!numberEntered.isEmpty() && (lhs.isEmpty())) { // IF STATE 2
            // GOTO STATE 3
            lhs = numberEntered;
            numberEntered = "";
        }

        // only perform the operation when there is both an lhs and an rhs
        if ((!lhs.isEmpty()) && (!rhs.isEmpty())) { // IF STATE 7 or STATE 8
            // perform operation
            switch (currentOperator) {
                case EQUAL_TO:
                    // display whatever result is there from last computation
                    results_textView.setText(result);
                    break;
                case DIVIDE:
                    resultInt = Integer.parseInt(lhs) / Integer.parseInt(rhs);
                    result = String.valueOf(resultInt);
                    break;
                case ADD:
                    resultInt = Integer.parseInt(lhs) + Integer.parseInt(rhs);
                    result = String.valueOf(resultInt);
                    break;
                case MULTIPLY:
                    resultInt = Integer.parseInt(lhs) * Integer.parseInt(rhs);
                    result = String.valueOf(resultInt);
                    break;
                case SUBTRACT:
                    resultInt = Integer.parseInt(lhs) - Integer.parseInt(rhs);
                    result = String.valueOf(resultInt);
                    break;
            }

            results_textView.setText(result);
            currentOperator = incomingOperator;

            // empty out lhs
            lhs = "";
            // move result to lhs
            lhs = result;
            // empty out rhs
            rhs = "";
        } else {
            // check if lhs is not empty - this means that the next number entered (if not empty)
            // needs to be assigned to rhs
            // empty out number entered
            if (!lhs.isEmpty() && (!numberEntered.isEmpty())) { // IF STATE 4
                rhs = numberEntered;
                numberEntered = "";
            }
        }
    }


    /*
     *       1 !entered !lhs !rhs
     *       2  entered !lhs !rhs
     *       3 !entered  lhs !rhs
     *       4  entered  lhs !rhs
     *       5 !entered !lhs  rhs
     *       6  entered !lhs  rhs
     *       7 !entered  lhs  rhs
     *       8  entered  lhs  rhs
     */
    void processOperationExhaustive(@Operation.Operator int operator) {
        if (numberEntered.isEmpty() && lhs.isEmpty() && rhs.isEmpty()) {             // STATE 1
            // do nothing because they pressed an operator with nothing entered, just ignore
            // alternatively could treat this as them having entered 0?
            // can just delete this block and start the next as if?
        } else if (!numberEntered.isEmpty() && lhs.isEmpty() && rhs.isEmpty()) {     // STATE 2
            // keep entered as left
            lhs = numberEntered;
            // clear entered
            numberEntered = "";
            // save operator int to instance
            // ... but what if they pressed equal?
            currentOperator = operator;
        } else if (numberEntered.isEmpty() && !lhs.isEmpty() && rhs.isEmpty()) {     // STATE 3
            // save operator int to instance (because they decided to change the operator?)
            // ... but what if they pressed equal?
            currentOperator = operator;
        } else if (!numberEntered.isEmpty() && !lhs.isEmpty() && rhs.isEmpty()) {    // STATE 4
            // do operation @, an expression based on what the operator is (your big case statement),
            // // effectively:
            // numberEntered = leftValueStr @ numberEntered
            // clear left?
             lhs = "";
        } else if (numberEntered.isEmpty() && lhs.isEmpty() && !rhs.isEmpty()) {     // STATE 5
            // delete this block? maybe never needs to be reached because maybe we don't need rightValueStr?
        } else if (!numberEntered.isEmpty() && lhs.isEmpty() && !rhs.isEmpty()) {    // STATE 6
            // delete this block? maybe never needs to be reached because maybe we don't need rightValueStr?
        } else if (numberEntered.isEmpty() && !lhs.isEmpty() && !rhs.isEmpty()) {    // STATE 7
            // delete this block? maybe never needs to be reached because maybe we don't need rightValueStr?
        } else if (!numberEntered.isEmpty() && !lhs.isEmpty() && !rhs.isEmpty()) {   // STATE 8
            // delete this block? maybe never needs to be reached because maybe we don't need rightValueStr?
        }
    }
}
