package com.example.vanahel.calculatorapplication.event.listener.custom.radigroup;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.widget.EditText;
import android.widget.RadioGroup;
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

public class CustomTwoRadioGroupListener implements RadioGroup.OnCheckedChangeListener {

    private Activity activity;
    private Float calculationResult;
    private EditText firstNumber;
    private EditText secondNumber;
    private EditText result;
    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;

    public CustomTwoRadioGroupListener (Activity activity) {
        this.activity = activity;
        firstNumber = (EditText) this.activity.findViewById(R.id.calculator_layout_num1);
        secondNumber = (EditText) this.activity.findViewById(R.id.calculator_layout_num2);
        result = (EditText) this.activity.findViewById(R.id.calculator_layout_result);
        radioGroup1 = (RadioGroup) this.activity.findViewById(R.id.rg_operations_min_multpl_land);
        radioGroup2 = (RadioGroup) this.activity.findViewById(R.id.rg_operations_plus_div_land);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
        CalculatorOperation operation;
        Boolean changeGroup = false;
        try {
            float frstNum = Float.parseFloat(firstNumber.getText().toString());
            float scndNum = Float.parseFloat(secondNumber.getText().toString());
            if (radioGroup != null && checkedId > -1 && changeGroup == false){
                if (radioGroup == radioGroup1) {
                    changeGroup = true;
                    radioGroup2.clearCheck();
                    radioGroup1.check(checkedId);
                    changeGroup = false;
                } else if (radioGroup == radioGroup2) {
                    changeGroup = true;
                    radioGroup1.clearCheck();
                    radioGroup2.check(checkedId);
                    changeGroup = false;
                }
            }
            switch (checkedId) {
                case R.id.radio_plus_land:
                    operation = new PlusOperation();
                    calculationResult = operation.perform(frstNum, scndNum);
                    break;
                case R.id.radio_divide_land:
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
                case R.id.radio_minus_land:
                    operation = new MinusOperation();
                    calculationResult = operation.perform(frstNum, scndNum);
                    break;
                case R.id.radio_multiple_land:
                    operation = new MultipleOperation();
                    calculationResult = operation.perform(frstNum, scndNum);
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this.activity.getApplicationContext(),
                    R.string.empty_number_field_message, Toast.LENGTH_LONG).show();
        }
    }

    public Float getCalculationResult() {
        return calculationResult;
    }
}