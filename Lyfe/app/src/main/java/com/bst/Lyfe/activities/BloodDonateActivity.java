package com.bst.Lyfe.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
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
    TextView textview,textday;
    ActivityChanger activityChanger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donate_blood_activity);
        activityChanger = new ActivityChanger(BloodDonateActivity.this);
        expandable_list = (ExpandableListView) findViewById(R.id.expandable_list);
        textview=(TextView)findViewById(R.id.textview);
        textview.setText(""+getResources().getString(R.string.donate_blood));
         textday= (TextView)findViewById(R.id.textday);
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
        parentItems.add(""+getResources().getString(R.string.donating_blood));
        parentItems.add(""+getResources().getString(R.string.about_blood));


    }


    public void setChildData() {


        ArrayList<String> child = new ArrayList<String>();
        child.add(""+getResources().getString(R.string.i_am_eligible));
        child.add(""+getResources().getString(R.string.detaild));
        child.add(""+getResources().getString(R.string.why_donate));
        child.add(""+getResources().getString(R.string.process_donation));
        child.add(""+getResources().getString(R.string.diff_donate));
        childItems.add(child);

        child = new ArrayList<String>();
        child.add(""+getResources().getString(R.string.what_are));
        child.add(""+getResources().getString(R.string.inheritance_of_group));
        child.add(""+getResources().getString(R.string.blood_components));
        childItems.add(child);


//        child = new ArrayList<String>();
//        child.add("Religious");
//        child.add("Culture Activities");
//        child.add("Language");
//        child.add("Dance & Music");
//        child.add("Shilpgram");
//        childItems.add(child);


    }

}
