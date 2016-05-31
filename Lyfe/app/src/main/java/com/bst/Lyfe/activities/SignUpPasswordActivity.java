package com.bst.Lyfe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bst.utils.ActivityChanger;

import com.bst.Lyfe.R;

/**
 * Created by arun on 27-05-2016.
 */
public class SignUpPasswordActivity extends AppCompatActivity {

    Button _buttonPass;
    EditText _enterPass, _reenterPass;
    ActivityChanger activityChanger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_password);

        _buttonPass = (Button) findViewById(R.id.submitPass);
        _enterPass = (EditText) findViewById(R.id.edittextpassword);
        _reenterPass = (EditText) findViewById(R.id.edittextrepassword);
        activityChanger = new ActivityChanger(SignUpPasswordActivity.this);
        _buttonPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_enterPass.getText().toString().trim().length() >= 6 && _enterPass.getText().toString().trim().length() >= 6) {
                    if (_enterPass.getText().toString().trim().equals(_enterPass.getText().toString().trim())) {
                        activityChanger.startActivity(new Intent(SignUpPasswordActivity.this, SignUpMobileVerificationActivity.class));
                    } else {
                        Snackbar.make(view, "Password was not match", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } else

                {
                    Snackbar.make(view, "Please enter 6 digit password", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
