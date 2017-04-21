package com.example.vanahel.calculatorapplication.event.listener.custom.radigroup;

import android.support.annotation.IdRes;
import android.widget.RadioGroup;

import com.example.vanahel.calculatorapplication.calculator.Calculator;
import com.example.vanahel.calculatorapplication.operation.CalculatorOperation;

/**
 * Created by lvvanahel on 21.04.17.
 */

public class OperationsTwoRadioGroupListener extends OperationsRadioGroupListener {

    private Calculator calculator;
    private CalculatorOperation operation;
    private RadioGroup radioGroupOne;
    private RadioGroup radioGroupTwo;


    public OperationsTwoRadioGroupListener(Calculator calculator,
                                           RadioGroup radioGroupOne, RadioGroup radioGroupTwo) {
        super(calculator);
        this.radioGroupOne = radioGroupOne;
        this.radioGroupTwo = radioGroupTwo;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
        if (radioGroup != null && checkedId > -1) {
            if (radioGroup == radioGroupOne) {
                radioGroupTwo.clearCheck();
                radioGroupOne.check(checkedId);
            } else if (radioGroup == radioGroupTwo) {
                radioGroupOne.clearCheck();
                radioGroupTwo.check(checkedId);
            }
        }
        super.onCheckedChanged(radioGroup, checkedId);
    }
}

