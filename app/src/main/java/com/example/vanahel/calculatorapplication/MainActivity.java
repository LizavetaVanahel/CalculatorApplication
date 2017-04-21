package com.example.vanahel.calculatorapplication;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.vanahel.calculatorapplication.calculator.Calculator;
import com.example.vanahel.calculatorapplication.event.listener.custom.menu.CustomMenuListener;
import com.example.vanahel.calculatorapplication.event.listener.custom.radigroup.OperationsRadioGroupListener;
import com.example.vanahel.calculatorapplication.event.listener.custom.radigroup.OperationsTwoRadioGroupListener;
import com.example.vanahel.calculatorapplication.operation.CalculatorOperation;
import com.example.vanahel.calculatorapplication.provider.calculator.CalculatorProvider;


public class MainActivity extends AppCompatActivity {

    private EditText firstNumber;
    private EditText secondNumber;
    private Calculator calculator;
    private Float calculationResult;
    private CalculatorProvider calculatorProvider;
    OperationsRadioGroupListener operationsRadioGroupListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rg_operations);
        final RadioGroup rdGrLandMinMultpl
                = (RadioGroup) findViewById(R.id.rg_operations_min_multpl_land);
        final RadioGroup rdGrLandPlusDivd
                = (RadioGroup) findViewById(R.id.rg_operations_plus_div_land);
        final Button calcResultBtn = (Button) findViewById(R.id.button_result);
        final Button menu = (Button) findViewById(R.id.button_clear);
        final EditText result = (EditText) findViewById(R.id.calculator_layout_result);
        final CustomMenuListener customMenuListener = new CustomMenuListener(MainActivity.this);
        final  EditText firstNumber = (EditText) findViewById(R.id.calculator_layout_num1);
        final EditText secondNumber = (EditText) findViewById(R.id.calculator_layout_num2);

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

            calculator = CalculatorProvider.getCalculator();
            operationsRadioGroupListener = new OperationsRadioGroupListener(calculator);
            radioGroup.setOnCheckedChangeListener(operationsRadioGroupListener);

            calcResultBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    try {
                    float frstNum = Float.parseFloat(firstNumber.getText().toString());
                    float scndNum = Float.parseFloat(secondNumber.getText().toString());
                    CalculatorOperation calculatorOperation= calculator.getCurrentOperation();
                    calculationResult =  calculatorOperation.perform(frstNum, scndNum);
                        result.setText(String.format("%f", calculationResult));
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), R.string.operation_not_found_message,
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {

            calculator = CalculatorProvider.getCalculator();
            OperationsTwoRadioGroupListener operationsTwoRadioGroupListener =
                    new OperationsTwoRadioGroupListener(calculator,rdGrLandMinMultpl, rdGrLandPlusDivd);
            rdGrLandMinMultpl.setOnCheckedChangeListener(operationsTwoRadioGroupListener);
            rdGrLandPlusDivd.setOnCheckedChangeListener(operationsTwoRadioGroupListener);

            calcResultBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                    float frstNum = Float.parseFloat(firstNumber.getText().toString());
                    float scndNum = Float.parseFloat(secondNumber.getText().toString());
                    CalculatorOperation calculatorOperation= calculator.getCurrentOperation();
                    calculationResult =  calculatorOperation.perform(frstNum, scndNum);
                    result.setText(String.format("%f", calculationResult));
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), R.string.operation_not_found_message,
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        menu.setOnClickListener(customMenuListener); }

    public void onClick(View view) {
        try {
            boolean checked = ((CheckBox) view).isChecked();
            firstNumber = (EditText) findViewById(R.id.calculator_layout_num1);
            secondNumber = (EditText) findViewById(R.id.calculator_layout_num2);
            switch (view.getId()) {
                case R.id.checkbox_float:
                    if (!checked) {
                        firstNumber.getText().clear();
                        secondNumber.getText().clear();
                        firstNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
                        secondNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
                    } else {
                        firstNumber.setInputType(InputType.TYPE_CLASS_NUMBER |
                                InputType.TYPE_NUMBER_FLAG_DECIMAL);
                        secondNumber.setInputType(InputType.TYPE_CLASS_NUMBER |
                                InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    }
                    break;
                case R.id.checkbox_signed:
                    if (!checked) {
                        firstNumber.getText().clear();
                        secondNumber.getText().clear();
                        firstNumber.setInputType(InputType.TYPE_CLASS_NUMBER );
                        secondNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
                    } else {
                        firstNumber.setInputType(InputType.TYPE_CLASS_NUMBER |
                                InputType.TYPE_NUMBER_FLAG_SIGNED);
                        secondNumber.setInputType(InputType.TYPE_CLASS_NUMBER |
                                InputType.TYPE_NUMBER_FLAG_SIGNED);
                    }
                    break;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), R.string.empty_number_field_message,
                    Toast.LENGTH_LONG).show();
        }
    }
}