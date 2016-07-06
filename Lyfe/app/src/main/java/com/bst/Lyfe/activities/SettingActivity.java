package com.bst.Lyfe.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bst.Lyfe.R;
import com.bst.utils.ActivityChanger;

/**
 * Created by arun on 04-07-2016.
 */
public class SettingActivity extends AppCompatActivity {
    ActivityChanger activityChanger;

    RelativeLayout layout_mpin, layout_logout, layout_notification;

    public Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Settings");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        activityChanger = new ActivityChanger(SettingActivity.this);
        layout_logout = (RelativeLayout) findViewById(R.id.layoutlogout);
        layout_mpin = (RelativeLayout) findViewById(R.id.layoutMpin);
        layout_notification = (RelativeLayout) findViewById(R.id.layoutnotification);

        layout_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog customDialog = new AlertDialog.Builder(SettingActivity.this)
                        .create();
                customDialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable(android.graphics.Color.TRANSPARENT));

                LayoutInflater inflater = (LayoutInflater) SettingActivity.this
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout dialoglayout = (LinearLayout) inflater.inflate(
                        R.layout.custom_dialog, null);

                TextView tvTitle = (TextView) dialoglayout
                        .findViewById(R.id.dialog_title);
                TextView tvDescription = (TextView) dialoglayout
                        .findViewById(R.id.dialog_description);
                Button button1 = (Button) dialoglayout.findViewById(R.id.dialog_done1);
                Button button2 = (Button) dialoglayout.findViewById(R.id.dialog_done2);

                tvTitle.setText(getResources().getString(R.string.logout));

                customDialog.setView(dialoglayout, 0, 0, 0, 0);

                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);

                customDialog.setCancelable(false);
                button2.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        customDialog.dismiss();
                        activityChanger.startActivity(new Intent(SettingActivity.this, SignInActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

                    }
                });
                button1.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        customDialog.dismiss();

                    }
                });

                customDialog.show();
            }

        });

        layout_mpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityChanger.startActivity(new Intent(SettingActivity.this, MobilePinActivity.class));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
