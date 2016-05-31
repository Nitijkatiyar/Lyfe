package com.bst.Lyfe.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bst.Lyfe.R;

/**
 * Created by arun on 27-05-2016.
 */
public class SignUpPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_password);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
