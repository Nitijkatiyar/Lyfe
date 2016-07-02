package com.bst.Lyfe.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bst.Lyfe.R;
import com.bst.Lyfe.adapter.BloodFactsAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by arun on 30-06-2016.
 */
public class ActivityBloodDonateInfo extends AppCompatActivity {
    RecyclerView recyclerview;


    TextView headertext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodfacts);
        List<String> eligibilty = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.eligibilty)));
        List<String> detail_eligibilty = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.detail_eligibilty)));
        List<String> why_should_donate_blood = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.why_should_donate_blood)));
        List<String> donationprocess = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.donationprocess)));
        List<String> different_type_blood_donation = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.different_type_blood_donation)));
        List<String> blood_groups = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.blood_groups)));
        List<String> inheritance_bloodgroups = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.inheritance_bloodgroups)));
        List<String> bloodcomponents = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.bloodcomponents)));
        List<String> day_blood_donor = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.day_blood_donor)));
        headertext = (TextView) findViewById(R.id.headertext);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layoutManager);
        Intent intent= getIntent();
        BloodFactsAdapter arrayAdapter = null;
        if (intent.hasExtra("eligibilty") && intent.getStringExtra("eligibilty").equals("eligibilty")) {
            headertext.setText("Am i eligible");
            arrayAdapter = new BloodFactsAdapter(this, eligibilty, "String");
        } else if (intent.hasExtra("detail_eligibilty") &&  intent.getStringExtra("detail_eligibilty").equalsIgnoreCase("detail_eligibilty")) {
            headertext.setText("Detailed eligibility criteria");
            arrayAdapter = new BloodFactsAdapter(this, detail_eligibilty, "String");
        } else if (intent.hasExtra("why_should_donate_blood") &&  intent.getStringExtra("why_should_donate_blood").equalsIgnoreCase("why_should_donate_blood")) {
            headertext.setText("Why should donate blood");
            arrayAdapter = new BloodFactsAdapter(this, why_should_donate_blood, "String");
        } else if (intent.hasExtra("donationprocess") &&  intent.getStringExtra("donationprocess").equalsIgnoreCase("donationprocess")) {
            headertext.setText("Donation Process");
            arrayAdapter = new BloodFactsAdapter(this, donationprocess, "String");
        } else if (intent.hasExtra("different_type_blood_donation") &&  intent.getStringExtra("different_type_blood_donation").equalsIgnoreCase("different_type_blood_donation")) {
            headertext.setText("Different ways to donate");
            arrayAdapter = new BloodFactsAdapter(this, different_type_blood_donation, "String");
        } else if ( intent.hasExtra("blood_groups") && intent.getStringExtra("blood_groups").equalsIgnoreCase("blood_groups")) {
            headertext.setText("What are blood groups");
            arrayAdapter = new BloodFactsAdapter(this, blood_groups, "String");
        } else if (intent.hasExtra("inheritance_bloodgroups") &&  intent.getStringExtra("inheritance_bloodgroups").equalsIgnoreCase("inheritance_bloodgroups")) {
            headertext.setText("Inheritance of blood groups");
            arrayAdapter = new BloodFactsAdapter(this, inheritance_bloodgroups, "String");
        } else if (intent.hasExtra("bloodcomponents") && intent.getStringExtra("bloodcomponents").equalsIgnoreCase("bloodcomponents")) {
            headertext.setText("Blood components");
            arrayAdapter = new BloodFactsAdapter(this, bloodcomponents, "String");
        } else if (intent.hasExtra("day_blood_donor") && intent.getStringExtra("day_blood_donor").equalsIgnoreCase("day_blood_donor")) {
            headertext.setText("World Blood Donor Day");
            arrayAdapter = new BloodFactsAdapter(this, day_blood_donor, "String");
        }


        recyclerview.setAdapter(arrayAdapter);

    }
}
