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

import com.example.vanahel.calculatorapplication.event.listener.custom.menu.CustomMenuListener;
import com.example.vanahel.calculatorapplication.event.listener.custom.radigroup.CustomRadioGroupListener;
import com.example.vanahel.calculatorapplication.event.listener.custom.radigroup.CustomTwoRadioGroupListener;


public class MainActivity extends AppCompatActivity {

    private EditText firstNumber;
    private EditText secondNumber;

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
        final CustomRadioGroupListener customRadioGroupListener
                = new CustomRadioGroupListener(MainActivity.this);
        final CustomTwoRadioGroupListener customTwoRadioGroupListener =
                new CustomTwoRadioGroupListener(MainActivity.this);
        final CustomMenuListener customMenuListener = new CustomMenuListener(MainActivity.this);

        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            radioGroup.setOnCheckedChangeListener(customRadioGroupListener);
            calcResultBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    try {
                        result.setText(customRadioGroupListener.getCalculationResult().toString());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), R.string.operation_not_found_message,
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            rdGrLandMinMultpl.setOnCheckedChangeListener(customTwoRadioGroupListener);
            rdGrLandPlusDivd.setOnCheckedChangeListener(customTwoRadioGroupListener);
            calcResultBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        result.setText(customTwoRadioGroupListener.getCalculationResult().toString());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), R.string.operation_not_found_message,
                                Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        menu.setOnClickListener(customMenuListener); }

    public void onCkeckboxClicked (View view) {
        try {
            boolean checked = ((CheckBox) view).isChecked();
            firstNumber = (EditText) findViewById(R.id.calculator_layout_num1);
            secondNumber = (EditText) findViewById(R.id.calculator_layout_num2);
            switch (view.getId()) {
                case R.id.checkbox_float:
                    if (!checked) {
                        firstNumber.getText().clear();
                        secondNumber.getText().clear();
                        firstNumber.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                        secondNumber.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                    } else {
                        firstNumber.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                        secondNumber.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    }
                    break;
                case R.id.checkbox_signed:
                    if (!checked) {
                        firstNumber.getText().clear();
                        secondNumber.getText().clear();
                        firstNumber.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                        secondNumber.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                    } else {
                        firstNumber.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
                        secondNumber.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
                    }
                    break;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), R.string.empty_number_field_message,
                    Toast.LENGTH_LONG).show();
        }
    }
}