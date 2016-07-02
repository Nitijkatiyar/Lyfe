package com.bst.Lyfe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.bst.Lyfe.R;
import com.bst.utils.ActivityChanger;

/**
 * Created by arun on 27-05-2016.
 */
public class SignUpPasswordActivity extends AppCompatActivity {

    Button _buttonPass;
    EditText _enterPass, _reenterPass;
    CheckBox termsNCondition;
    TextView termsNConditionText;
    ActivityChanger activityChanger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_password);

        activityChanger = new ActivityChanger(SignUpPasswordActivity.this);

        termsNCondition = (CheckBox) findViewById(R.id.TnC);
        termsNConditionText = (TextView) findViewById(R.id.tnctext);

        termsNConditionText.setText(Html.fromHtml("I have read and accept the <span style=\"color:#7777f3;\"><u>Terms &amp; Conditions</u></span>"));
        _buttonPass = (Button) findViewById(R.id.submitPass);
        _enterPass = (EditText) findViewById(R.id.edittextpassword);
        _reenterPass = (EditText) findViewById(R.id.edittextrepassword);
        activityChanger = new ActivityChanger(SignUpPasswordActivity.this);
        _buttonPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_enterPass.getText().toString().trim().length() >= 6 && _reenterPass.getText().toString().trim().length() >= 6) {
                    if (_enterPass.getText().toString().trim().equals(_reenterPass.getText().toString().trim())) {
                        activityChanger.startActivity(new Intent(SignUpPasswordActivity.this, MainActivity.class));
                    } else {
                        Snackbar.make(view, "Password did not match", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } else

                {
                    Snackbar.make(view, "Please enter atleast 6 digit password", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });


        termsNConditionText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityChanger.startActivity(new Intent(SignUpPasswordActivity.this, DisclaimerActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
