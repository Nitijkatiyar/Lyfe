package com.bst.Lyfe.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bst.Lyfe.R;
import com.bst.utils.ActivityChanger;
import com.bst.utils.Preferences;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    Button _login, button_facebook, button_google;
    TextView _signup;
    EditText _userName, _password;
    ActivityChanger activityChanger;
    private HashMap<String, String> userHashmap;
    private ArrayList<HashMap<String, String>> friendList;
    private ProgressDialog pd;
    private GoogleSignInOptions gso;

    //google api client
    private GoogleApiClient mGoogleApiClient;

    //Signin constant to check the activity result
    private int RC_SIGN_IN = 100;


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

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        button_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFacebookUserInfo();
                //activityChanger.startActivity(new Intent(SignInActivity.this, MainActivity.class));

            }
        });

        button_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
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

                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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

    private void signIn() {
        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //If signin
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
        }
    }


    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        if (result.isSuccess()) {
            //Getting google account
            GoogleSignInAccount acct = result.getSignInAccount();
            Preferences.setPrefrences(this, Preferences.USER_ID, acct.getId());
            Preferences.setPrefrences(this, Preferences.NAME, acct.getDisplayName());
            Preferences.setPrefrences(this, Preferences.USER_NAME, acct.getEmail());
            Log.e("Email",""+acct.getEmail());
            Log.e("UserName",""+acct.getDisplayName());
            Intent intent = new Intent(SignInActivity.this,MainActivity.class);
            startActivity(intent);

            //Displaying name and email
//            textViewName.setText(acct.getDisplayName());
//            textViewEmail.setText(acct.getEmail());
//
//            //Initializing image loader
//            imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext())
//                    .getImageLoader();
//
//            imageLoader.get(acct.getPhotoUrl().toString(),
//                    ImageLoader.getImageListener(profilePhoto,
//                            R.mipmap.ic_launcher,
//                            R.mipmap.ic_launcher));
//
//            //Loading image
//            profilePhoto.setImageUrl(acct.getPhotoUrl().toString(), imageLoader);

        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
