package com.bst.Lyfe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bst.Lyfe.R;
import com.bst.utils.ActivityChanger;

/**
 * Created by arun on 22-06-2016.
 */
public class MPINSignInActivity extends AppCompatActivity implements View.OnClickListener {
    public Toolbar toolbar;
    Button number1, number2, number3, number4, number5, number6, number7, number8, number9, number0, backpress;
    EditText editText;
    ActivityChanger activityChanger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpinsignin);

        activityChanger = new ActivityChanger(MPINSignInActivity.this);

        number0 = (Button) findViewById(R.id.number0);
        number1 = (Button) findViewById(R.id.number1);
        number2 = (Button) findViewById(R.id.number2);
        number3 = (Button) findViewById(R.id.number3);
        number4 = (Button) findViewById(R.id.number4);
        number5 = (Button) findViewById(R.id.number5);
        number6 = (Button) findViewById(R.id.number6);
        number7 = (Button) findViewById(R.id.number7);
        number8 = (Button) findViewById(R.id.number8);
        number9 = (Button) findViewById(R.id.number9);
        backpress = (Button) findViewById(R.id.backspace);

        editText = (EditText) findViewById(R.id.edittext);


        number0.setOnClickListener(this);
        number1.setOnClickListener(this);
        number2.setOnClickListener(this);
        number3.setOnClickListener(this);
        number4.setOnClickListener(this);
        number5.setOnClickListener(this);
        number6.setOnClickListener(this);
        number7.setOnClickListener(this);
        number8.setOnClickListener(this);
        number9.setOnClickListener(this);
        backpress.setOnClickListener(this);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText.getText().toString().trim().length() == 4) {
                    activityChanger.startActivity(new Intent(MPINSignInActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
                }
            }
        });

    }


    @Override
    public void onClick(View v) {
        if (v.equals(number0)) {
            editText.setText(editText.getText().toString().trim() + "0");
        } else if (v.equals(number1)) {
            editText.setText(editText.getText().toString().trim() + "1");
        } else if (v.equals(number2)) {
            editText.setText(editText.getText().toString().trim() + "2");
        } else if (v.equals(number3)) {
            editText.setText(editText.getText().toString().trim() + "3");
        } else if (v.equals(number4)) {
            editText.setText(editText.getText().toString().trim() + "4");
        } else if (v.equals(number5)) {
            editText.setText(editText.getText().toString().trim() + "5");
        } else if (v.equals(number6)) {
            editText.setText(editText.getText().toString().trim() + "6");
        } else if (v.equals(number7)) {
            editText.setText(editText.getText().toString().trim() + "7");
        } else if (v.equals(number8)) {
            editText.setText(editText.getText().toString().trim() + "8");
        } else if (v.equals(number9)) {
            editText.setText(editText.getText().toString().trim() + "9");
        } else if (v.equals(backpress)) {
            if (editText.getText().toString().trim().length() > 0) {
                editText.setText(editText.getText().toString().trim().substring(0, editText.getText().toString().trim().length() - 1) + "9");
            }
        }
    }
}
