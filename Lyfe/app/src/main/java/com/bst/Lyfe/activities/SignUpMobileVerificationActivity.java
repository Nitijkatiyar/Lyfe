package com.bst.Lyfe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bst.Lyfe.R;
import com.bst.utils.ActivityChanger;

public class SignUpMobileVerificationActivity extends AppCompatActivity {

    Button _validateOTP;
    TextView _signin;
    EditText _mobileNumber, _otp;
    ActivityChanger activityChanger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_mobileverification);

        activityChanger = new ActivityChanger(SignUpMobileVerificationActivity.this);

        _validateOTP = (Button) findViewById(R.id.buttonValidatePIN);
        _signin = (TextView) findViewById(R.id.textviewSignin);
        _mobileNumber = (EditText) findViewById(R.id.edittextMobileNumber);
        _otp = (EditText) findViewById(R.id.edittextOTP);

        _validateOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_mobileNumber.getText().toString().trim().length() >= 10) {
                    if (_otp.getText().toString().trim().length() == 6) {
                        activityChanger.startActivity(new Intent(SignUpMobileVerificationActivity.this, SignUpBasicInformationActivity.class));
                    } else {
                        Snackbar.make(v, "Enter a valid OTP", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } else {
                    Snackbar.make(v, "Enter valid mobile number", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        _signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityChanger.startActivity(new Intent(SignUpMobileVerificationActivity.this, SignInActivity.class));
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
