package com.example.vanahel.calculatorapplication.provider.calculator;

import com.example.vanahel.calculatorapplication.calculator.Calculator;

/**
 * Created by lvvanahel on 20.04.17.
 */

public class CalculatorProvider {

    private static Calculator calculator;

    private CalculatorProvider(){
    }

    public static Calculator getCalculator(){
        if (calculator == null){
            calculator = new Calculator();
        }
        return calculator;
    }


}
