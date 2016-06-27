package com.bst.Lyfe.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bst.Lyfe.R;
import com.bst.utils.ActivityChanger;
import com.bst.utils.Preferences;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {

    Button _login, button_facebook, button_google;
    TextView _signup;
    EditText _userName, _password;
    ActivityChanger activityChanger;
    private HashMap<String, String> userHashmap;
    private ArrayList<HashMap<String, String>> friendList;
    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getKeyHash();
        activityChanger = new ActivityChanger(SignInActivity.this);


        _login = (Button) findViewById(R.id.buttonLogin);
        button_facebook = (Button) findViewById(R.id.button_facebook);
        button_google = (Button) findViewById(R.id.button_google);


        _signup = (TextView) findViewById(R.id.textviewSignup);
        _userName = (EditText) findViewById(R.id.edittextUsername);
        _password = (EditText) findViewById(R.id.edittextPassword);
        button_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFacebookUserInfo();
                //activityChanger.startActivity(new Intent(SignInActivity.this, MainActivity.class));

            }
        });

        _signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityChanger.startActivity(new Intent(SignInActivity.this, SignUpMobileVerificationActivity.class));

            }
        });

        _login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_userName.getText().toString().length() > 0 && _password.getText().toString().length() > 0) {
                    if (_password.getText().toString().trim().length() >= 6) {
                        activityChanger.startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    } else {
                        Snackbar.make(v, "Password is too short", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } else {
                    Snackbar.make(v, "Please Fill all Details", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }


            }
        });
    }

    private void getFacebookUserInfo() {
        Session.openActiveSession(this, true, new Session.StatusCallback() {

            @Override
            public void call(Session session, SessionState state,
                             Exception exception) {

                if (session.isOpened()) {
                    boolean isPermissionAvailable = false;
                    for (int i = 0; i < session.getPermissions().size(); i++) {
                        if (session.getPermissions().get(i).contains("email")) {
                            pd = ProgressDialog.show(SignInActivity.this, "", "");
                            isPermissionAvailable = true;

                            Request.newMeRequest(session,
                                    new Request.GraphUserCallback() {

                                        @Override
                                        public void onCompleted(
                                                final GraphUser user,
                                                Response response) {

                                            if (user != null) {
                                                getUserInfoFromFacebook(user);
                                            }
                                        }
                                    }).executeAsync();
                        }
                    }
                    if (!isPermissionAvailable)
                        getPermissionFromFacebook();
                }
            }
        });
    }

    private void getPermissionFromFacebook() {
        Intent intent = null;
        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com"));
        startActivity(intent);
        String[] permissions = {"basic_info", "user_friends", "email"};
        Session.getActiveSession().requestNewReadPermissions(
                new Session.NewPermissionsRequest(SignInActivity.this, Arrays
                        .asList(permissions)));
    }

    private void getUserInfoFromFacebook(final GraphUser user) {


        Preferences.setPrefrences(this, Preferences.USER_ID, user.getId());
        Preferences.setPrefrences(this, Preferences.NAME, user.getName());
        Preferences.setPrefrences(this, Preferences.USER_NAME, user.getUsername());
        Preferences.setPrefrences(this, Preferences.FIRST_NAME, user.getFirstName());
        Preferences.setPrefrences(this, Preferences.LAST_NAME, user.getLastName());
        Preferences.setPrefrences(this, Preferences.BIRTHDAY, user.getBirthday());
        Preferences.setPrefrences(this, Preferences.GENDER, (String) user.getProperty("gender"));
        Preferences.setPrefrences(this, Preferences.EMAIL_ID, user.asMap().get("email").toString());
        Log.e("email",""+user.asMap().get("email").toString());


    }

    private void getKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("Keyhash:",
                        Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }
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
