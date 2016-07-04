package com.bst.Lyfe.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.bst.Lyfe.R;
import com.bst.Lyfe.fragments.DonateFragment;
import com.bst.Lyfe.fragments.MoreFragment;
import com.bst.Lyfe.fragments.NotificationFragment;
import com.bst.Lyfe.fragments.ProfileFragment;
import com.bst.Lyfe.fragments.RequestBloodFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Boolean app_closed = false;
    public Toolbar toolbar;
    RelativeLayout tabDonate, tabRequest, tabNotification, tabProfile, tabMore;
    TextView tabTextDonate, tabTextRequest, tabTextNotification, tabTextProfile, tabTextMore;
    ImageView tabImageDonate, tabImageRequest, tabImageNotification, tabImageProfile, tabImageMore;
    LinearLayout mainLayout;
    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

        tabDonate = (RelativeLayout) findViewById(R.id.tab1);
        tabRequest = (RelativeLayout) findViewById(R.id.tab2);
        tabNotification = (RelativeLayout) findViewById(R.id.tab3);
        tabProfile = (RelativeLayout) findViewById(R.id.tab4);
        tabMore = (RelativeLayout) findViewById(R.id.tab5);

        tabTextDonate = (TextView) findViewById(R.id.tabtext1);
        tabTextRequest = (TextView) findViewById(R.id.tabtext2);
        tabTextNotification = (TextView) findViewById(R.id.tabtext3);
        tabTextProfile = (TextView) findViewById(R.id.tabtext4);
        tabTextMore = (TextView) findViewById(R.id.tabtext5);

        tabImageDonate = (ImageView) findViewById(R.id.tabimage1);
        tabImageRequest = (ImageView) findViewById(R.id.tabimage2);
        tabImageNotification = (ImageView) findViewById(R.id.tabimage3);
        tabImageProfile = (ImageView) findViewById(R.id.tabimage4);
        tabImageMore = (ImageView) findViewById(R.id.tabimage5);


        tabDonate.setOnClickListener(this);
        tabRequest.setOnClickListener(this);
        tabNotification.setOnClickListener(this);
        tabProfile.setOnClickListener(this);
        tabMore.setOnClickListener(this);

        toolbar.setTitle("Donate");
        tabImageDonate.setImageResource(R.mipmap.blood_donate_selected);
        tabTextDonate.setVisibility(View.VISIBLE);
        changeFragmentTo(new DonateFragment());


    }

    @Override
    public void onClick(View v) {
        resetTabs();
        if (v.equals(tabDonate)) {
            tabImageDonate.setImageResource(R.mipmap.blood_donate_selected);
            tabTextDonate.setVisibility(View.VISIBLE);
            toolbar.setTitle("Donate");
            changeFragmentTo(new DonateFragment());
        } else if (v.equals(tabRequest)) {
            tabImageRequest.setImageResource(R.mipmap.blood_request_selected);
            tabTextRequest.setVisibility(View.VISIBLE);
            toolbar.setTitle("Request");
            changeFragmentTo(new RequestBloodFragment());
        } else if (v.equals(tabNotification)) {
            tabImageNotification.setImageResource(R.mipmap.notification_selected);
            tabTextNotification.setVisibility(View.VISIBLE);
            toolbar.setTitle("Notification");
            changeFragmentTo(new NotificationFragment());
        } else if (v.equals(tabProfile)) {
            tabImageProfile.setImageResource(R.mipmap.user_profile_selected);
            tabTextProfile.setVisibility(View.VISIBLE);
            toolbar.setTitle("Profile");
            changeFragmentTo(new ProfileFragment());
        } else if (v.equals(tabMore)) {
            tabImageMore.setImageResource(R.mipmap.more_selected);
            tabTextMore.setVisibility(View.VISIBLE);
            toolbar.setTitle("More");
            changeFragmentTo(new MoreFragment());
        }
    }

    private void resetTabs() {
        tabImageDonate.setImageResource(R.mipmap.blood_donate_default);
        tabImageRequest.setImageResource(R.mipmap.blood_request_default);
        tabImageNotification.setImageResource(R.mipmap.notification_default);
        tabImageProfile.setImageResource(R.mipmap.user_profile_default);
        tabImageMore.setImageResource(R.mipmap.more_default);
        tabTextDonate.setVisibility(View.GONE);
        tabTextRequest.setVisibility(View.GONE);
        tabTextNotification.setVisibility(View.GONE);
        tabTextProfile.setVisibility(View.GONE);
        tabTextMore.setVisibility(View.GONE);
    }


    public void changeFragmentTo(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.menu_fragment, fragment).commit();
    }

    @Override
    public void onBackPressed() {
        closedApplication();
    }

    private void closedApplication() {
        if (app_closed) {
            finish();
            super.onBackPressed();
        }
        this.app_closed = true;
        Snackbar snackbar = Snackbar.make(mainLayout, "Press again to exit.", Snackbar.LENGTH_SHORT);
        snackbar.getView();
        TextView textView = (TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                app_closed = false;
            }
        }, 2000);
    }


}
