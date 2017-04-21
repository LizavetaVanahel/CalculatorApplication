package com.example.vanahel.calculatorapplication.event.listener.custom.radigroup;

import android.support.annotation.IdRes;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.vanahel.calculatorapplication.R;
import com.example.vanahel.calculatorapplication.calculator.Calculator;
import com.example.vanahel.calculatorapplication.operation.CalculatorOperation;
import com.example.vanahel.calculatorapplication.operation.DivideOperation;
import com.example.vanahel.calculatorapplication.operation.MinusOperation;
import com.example.vanahel.calculatorapplication.operation.MultipleOperation;
import com.example.vanahel.calculatorapplication.operation.PlusOperation;


/**
 * Created by lvvanahel on 16.04.17.
 */

public class OperationsRadioGroupListener implements OnCheckedChangeListener {

    private Calculator calculator;
    private CalculatorOperation operation;

    public OperationsRadioGroupListener (Calculator calculator){
        this.calculator = calculator;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
            switch (checkedId) {
                case R.id.radio_plus:
                    operation = new PlusOperation();
                    calculator.setCurrentOperation(operation);
                    break;
                case R.id.radio_minus:
                    operation = new MinusOperation();
                    calculator.setCurrentOperation(operation);
                    break;
                case R.id.radio_divide:
                        operation = new DivideOperation();
                        calculator.setCurrentOperation(operation);
                    break;
                case R.id.radio_multiple:
                    operation = new MultipleOperation();
                    calculator.setCurrentOperation(operation);
                    break;
                default:
                    break;
            }
    }

}
