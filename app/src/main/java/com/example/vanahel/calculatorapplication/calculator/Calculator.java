package com.example.vanahel.calculatorapplication.calculator;

import com.example.vanahel.calculatorapplication.operation.CalculatorOperation;

/**
 * Created by lvvanahel on 20.04.17.
 */

public class Calculator {

    private CalculatorOperation currentOperation;

    public void setCurrentOperation(CalculatorOperation currentOperation) {
        this.currentOperation = currentOperation;
    }

    public CalculatorOperation getCurrentOperation() {
        return this.currentOperation;
    }

}
