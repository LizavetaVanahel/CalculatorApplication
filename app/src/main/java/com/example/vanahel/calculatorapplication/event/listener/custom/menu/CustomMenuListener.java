package com.example.vanahel.calculatorapplication.event.listener.custom.menu;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.vanahel.calculatorapplication.R;


/**
 * Created by lvvanahel on 17.04.17.
 */

public class CustomMenuListener implements View.OnClickListener {

    private Activity activity;
    private EditText firstNumber;
    private EditText secondNumber;
    private EditText result;
    private Button menu;

    public CustomMenuListener(Activity activity) {
        this.activity = activity;
        firstNumber = (EditText) this.activity.findViewById(R.id.calculator_layout_num1);
        secondNumber = (EditText) this.activity.findViewById(R.id.calculator_layout_num2);
        result = (EditText) this.activity.findViewById(R.id.calculator_layout_result);
        menu = (Button) this.activity.findViewById(R.id.button_clear);
    }

    @Override
    public void onClick(View view) {
        PopupMenu popup = new PopupMenu(activity, menu);
        popup.getMenuInflater().inflate(R.menu.popupmenu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                try {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                    alertDialogBuilder.setTitle("Delete?");
                    alertDialogBuilder.setMessage("Are you sure you want to delete?");
                    alertDialogBuilder.setNegativeButton("Cancel", null);
                    alertDialogBuilder.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            firstNumber.getText().clear();
                            secondNumber.getText().clear();
                            result.getText().clear();
                        }
                    });
                    alertDialogBuilder.show();
                } catch (NumberFormatException e) {
                    Toast.makeText(
                            activity,

                            "The fields are empty",
                            Toast.LENGTH_SHORT
                    ).show();
                }
                return true;
            }
        });
        popup.show();
    }
}
