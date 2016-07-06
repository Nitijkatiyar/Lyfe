package com.bst.Lyfe.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bst.Lyfe.R;
import com.bst.Lyfe.adapter.MyExpandableAdapter;
import com.bst.utils.ActivityChanger;

import java.util.ArrayList;

/**
 * Created by arun on 30-06-2016.
 */
public class BloodDonateActivity extends AppCompatActivity {


    ExpandableListView expandable_list;
    private ArrayList<String> parentItems = new ArrayList<String>();
    private ArrayList<ArrayList<String>> childItems = new ArrayList();
    MyExpandableAdapter adapter;
    FrameLayout frameLayout;

    public Toolbar toolbar;

    TextView textday;
    ActivityChanger activityChanger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donate_blood_activity);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Donate Blood");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        activityChanger = new ActivityChanger(BloodDonateActivity.this);

        expandable_list = (ExpandableListView) findViewById(R.id.expandable_list);

        textday = (TextView) findViewById(R.id.textday);
        textday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                activityChanger.startActivity(new Intent(BloodDonateActivity.this, ActivityBloodDonateInfo.class).putExtra("day_blood_donor", "day_blood_donor"));

            }
        });
        setGroupParents();
        setChildData();
        expandable_list.setClickable(true);

        adapter = new MyExpandableAdapter(parentItems, childItems);
        adapter.setInflater(
                (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
                this);
        expandable_list.setAdapter(adapter);


    }

    public void setGroupParents() {
        parentItems.add("Donating Blood");
        parentItems.add("About Blood");


    }


    public void setChildData() {


        ArrayList<String> child = new ArrayList<String>();
        child.add("Am i eligible");
        child.add("Detailed eligibility criteria");
        child.add("Why should donate blood");
        child.add("Donation Process");
        child.add("Different ways to donate");
        childItems.add(child);

        child = new ArrayList<String>();
        child.add("What are blood groups");
        child.add("Inheritance of blood groups");
        child.add("Blood components");
        childItems.add(child);


//        child = new ArrayList<String>();
//        child.add("Religious");
//        child.add("Culture Activities");
//        child.add("Language");
//        child.add("Dance & Music");
//        child.add("Shilpgram");
//        childItems.add(child);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
