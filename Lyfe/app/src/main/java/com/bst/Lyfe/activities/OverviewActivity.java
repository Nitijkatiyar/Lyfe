package com.bst.Lyfe.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.bst.Lyfe.R;
import com.bst.utils.ActivityChanger;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class OverviewActivity extends Activity {
    ViewPager viewPager;
    PagerAdapter adapter;
    String[] titleText;
    String[] titleTop, titleMiddle, titlebottom;
    int[] images;
    private AdView adView;
    AdRequest.Builder adRequestBuilder = null;
    View indicator1, indicator2, indicator3, indicator4;
    Button signIn, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        indicator1 = (View) findViewById(R.id.indicator1);
        indicator2 = (View) findViewById(R.id.indicator2);
        indicator3 = (View) findViewById(R.id.indicator3);
        indicator4 = (View) findViewById(R.id.indicator4);
        indicator1.setBackgroundResource(R.color.colorPrimary);

        signUp = (Button) findViewById(R.id.signup);
        signIn = (Button) findViewById(R.id.signin);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActivityChanger(OverviewActivity.this).startActivity(new Intent(OverviewActivity.this, SignUpMobileVerificationActivity.class));
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ActivityChanger(OverviewActivity.this).startActivity(new Intent(OverviewActivity.this, SignInActivity.class));
            }
        });
        titleTop = new String[]{"" + getResources().getString(R.string.give_hope), "You Don't have to be a doctor to save lives",
                "You are a hero to someone, somewhere, who received your gracious gift of life", "Give the Gift of Life"};

        titleMiddle = new String[]{"Give Blood",
                "Just donate blood", "If you're a blood donor",
                "Donate Blood"};

        titlebottom = new String[]{"Give Life",
                "", "",
                ""};


        images = new int[]{R.mipmap.ic_launcher, R.drawable.sliderimage22,
                R.drawable.sliderimage3, R.drawable.sliderimage4};

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new OverviewViewPagerAdapter(OverviewActivity.this,
                titleTop, titleMiddle, titlebottom, images);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                refreshIndicator(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    private void refreshIndicator(int position) {
        indicator1.setBackgroundResource(R.color.light_grey);
        indicator2.setBackgroundResource(R.color.light_grey);
        indicator3.setBackgroundResource(R.color.light_grey);
        indicator4.setBackgroundResource(R.color.light_grey);

        if (position == 0) {
            indicator1.setBackgroundResource(R.color.colorPrimary);
        } else if (position == 1) {
            indicator2.setBackgroundResource(R.color.colorPrimary);
        } else if (position == 2) {
            indicator3.setBackgroundResource(R.color.colorPrimary);
        } else if (position == 3) {
            indicator4.setBackgroundResource(R.color.colorPrimary);
        }
    }

}
