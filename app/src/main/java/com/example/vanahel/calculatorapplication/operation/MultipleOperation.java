package com.example.vanahel.calculatorapplication.operation;

/**
 * Created by lvvanahel on 11.04.17.
 */

public class MultipleOperation extends CalculatorOperation {
    @Override
    public float perform(float flNum1, float flNum2) {
        return flNum1 * flNum2;
    }
}