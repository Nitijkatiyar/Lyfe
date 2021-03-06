package com.bst.Lyfe.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.bst.Lyfe.R;
import com.bst.utils.Preferences;

/**
 * Created by arun on 04-07-2016.
 */
public class MobilePinActivity extends AppCompatActivity {

    Button buttoncreatePin;
    EditText enterpin, confirmpin;
    public Toolbar toolbar;
    Intent intent;
    boolean pinUpdate = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpin);

        intent = getIntent();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (intent != null && intent.hasExtra("OPERATION")) {
            toolbar.setTitle("Create PIN");
            pinUpdate = false;
            setSupportActionBar(toolbar);
        } else {
            toolbar.setTitle("Change PIN");
            pinUpdate = true;
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        buttoncreatePin = (Button) findViewById(R.id.createpin);
        enterpin = (EditText) findViewById(R.id.edittextcratepin);
        confirmpin = (EditText) findViewById(R.id.edittextconfirmedPin);
        buttoncreatePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                if (enterpin.getText().toString().trim().length() > 5 && confirmpin.getText().toString().trim().length() > 5) {
                    if (enterpin.getText().toString().trim().equals(confirmpin.getText().toString().trim())) {
                        Preferences.setMobilePin(MobilePinActivity.this, enterpin.getText().toString().trim());
                        Preferences.setMobilePinActivated(MobilePinActivity.this, true);
                        Snackbar.make(view, "" + getResources().getString(R.string.pin_success), Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                        finish();

                    } else {
                        Snackbar.make(view, ""+getResources().getString(R.string.pin_not_match), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } else {
                    Snackbar.make(view, ""+getResources().getString(R.string.enter_6_digit), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
