package com.bst.Lyfe.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.bst.Lyfe.R;
import com.bst.Lyfe.fragments.DonateFragment;
import com.bst.Lyfe.fragments.MoreFragment;
import com.bst.Lyfe.fragments.NotificationFragment;
import com.bst.Lyfe.fragments.ProfileFragment;
import com.bst.Lyfe.fragments.RequestBloodFragment;
import com.bst.bottombar.BottomBar;
import com.bst.bottombar.BottomBarTab;
import com.bst.bottombar.OnTabClickListener;

public class MainActivity extends AppCompatActivity {
    private Boolean app_closed = false;
    public Toolbar toolbar;
    BottomBar mBottomBar;
    CoordinatorLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mainLayout = (CoordinatorLayout) findViewById(R.id.mainLayout);

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        // Disable the left bar on tablets and behave exactly the same on mobile and tablets instead.
        mBottomBar.noTabletGoodness();

        // Show all titles even when there's more than three tabs.
//        mBottomBar.useFixedMode();

        // Use the dark theme.
        mBottomBar.useDarkTheme();

        mBottomBar.setItems(
                new BottomBarTab(R.mipmap.blood_donate_selected, "Donate"),
                new BottomBarTab(R.mipmap.blood_request_selected, "Request"),
                new BottomBarTab(R.mipmap.notification_selected, "Notifications"),
                new BottomBarTab(R.mipmap.user_profile_selected, "Profile"),
                new BottomBarTab(R.mipmap.more_selected, "More")
        );


        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorWhite));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.colorWhite));
        mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.colorWhite));
        mBottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.colorWhite));

//        mBottomBar.setActiveTabColor(ContextCompat.getColor(this, R.color.colorWhite));

        mBottomBar.setDefaultTabPosition(0);

        changeFragmentTo(new DonateFragment());
        // Listen for tab changes
        mBottomBar.setOnTabClickListener(new OnTabClickListener() {
            @Override
            public void onTabSelected(int position) {
                if (position == 0) {
                    toolbar.setTitle("Donate");
                    changeFragmentTo(new DonateFragment());
                } else if (position == 1) {
                    toolbar.setTitle("Request");
                    changeFragmentTo(new RequestBloodFragment());
                } else if (position == 2) {
                    toolbar.setTitle("Notifications");
                    changeFragmentTo(new NotificationFragment());
                } else if (position == 3) {
                    toolbar.setTitle("Profile");
                    changeFragmentTo(new ProfileFragment());
                } else if (position == 4) {
                    toolbar.setTitle("More");
                    changeFragmentTo(new MoreFragment());
                }
            }

            @Override
            public void onTabReSelected(int position) {
                // The user reselected a tab at the specified position!
            }
        });
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
