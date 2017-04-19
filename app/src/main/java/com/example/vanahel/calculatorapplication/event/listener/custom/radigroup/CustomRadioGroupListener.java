package com.example.vanahel.calculatorapplication.event.listener.custom.radigroup;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.vanahel.calculatorapplication.R;
import com.example.vanahel.calculatorapplication.operation.CalculatorOperation;
import com.example.vanahel.calculatorapplication.operation.DivideOperation;
import com.example.vanahel.calculatorapplication.operation.MinusOperation;
import com.example.vanahel.calculatorapplication.operation.MultipleOperation;
import com.example.vanahel.calculatorapplication.operation.PlusOperation;


/**
 * Created by lvvanahel on 16.04.17.
 */

public class CustomRadioGroupListener implements OnCheckedChangeListener {

    private Activity activity;
    private Float calculationResult;
    private EditText firstNumber;
    private EditText secondNumber;
    private EditText result;

    public CustomRadioGroupListener(Activity activity) {
        this.activity = activity;
        firstNumber = (EditText) this.activity.findViewById(R.id.calculator_layout_num1);
        secondNumber = (EditText) this.activity.findViewById(R.id.calculator_layout_num2);

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
        CalculatorOperation operation;
        try {
            float frstNum = Float.parseFloat(firstNumber.getText().toString());
            float scndNum = Float.parseFloat(secondNumber.getText().toString());
            result = (EditText) this.activity.findViewById(R.id.calculator_layout_result);
            switch (checkedId) {
                case -1:
                    Toast.makeText(this.activity.getApplicationContext(), "No operation is selected",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.radio_plus:
                    operation = new PlusOperation();
                    calculationResult = operation.perform(frstNum, scndNum);
                    break;
                case R.id.radio_minus:
                    operation = new MinusOperation();
                    calculationResult = operation.perform(frstNum, scndNum);
                    break;
                case R.id.radio_divide:
                    if (scndNum == 0) {
                        secondNumber.getText().clear();
                        Toast.makeText(this.activity.getApplicationContext(),
                                R.string.divide_by_zero_message, Toast.LENGTH_LONG).show();
                    } else {
                        operation = new DivideOperation();
                        if (scndNum != 0) {
                            calculationResult = operation.perform(frstNum, scndNum);
                        }
                    }
                    break;
                case R.id.radio_multiple:
                    operation = new MultipleOperation();
                    calculationResult = operation.perform(frstNum, scndNum);
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this.activity.getApplicationContext(), R.string.empty_number_field_message,
                    Toast.LENGTH_LONG).show();

        }
    }

    public Float getCalculationResult() {
        return calculationResult;
    }
}
