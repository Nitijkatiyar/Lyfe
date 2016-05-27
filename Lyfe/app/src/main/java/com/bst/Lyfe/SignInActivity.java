package com.bst.Lyfe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bst.utils.ActivityChanger;

public class SignInActivity extends AppCompatActivity {

    Button _login;
    TextView _signup;
    EditText _userName, _password;
    ActivityChanger activityChanger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        activityChanger = new ActivityChanger(SignInActivity.this);

        _login = (Button) findViewById(R.id.buttonLogin);
        _signup = (TextView) findViewById(R.id.textviewSignup);
        _userName = (EditText) findViewById(R.id.edittextUsername);
        _password = (EditText) findViewById(R.id.edittextPassword);

        _login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityChanger.startActivity(new Intent(SignInActivity.this, MainActivity.class));
            }
        });

        _signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityChanger.startActivity(new Intent(SignInActivity.this, SignUpMobileVerificationActivity.class));
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
