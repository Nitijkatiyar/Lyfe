package com.bst.Lyfe.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bst.Lyfe.R;
import com.bst.Lyfe.activities.ActivityBloodDonateInfo;
import com.bst.Lyfe.activities.BloodDonateActivity;

import java.util.ArrayList;


public class MyExpandableAdapter extends BaseExpandableListAdapter {

    private BloodDonateActivity activity;
    private ArrayList<ArrayList<String>> childtems;
    private LayoutInflater inflater;
    private ArrayList<String> parentItems, child;


    public MyExpandableAdapter(ArrayList<String> parents,
                               ArrayList<ArrayList<String>> childern) {
        this.parentItems = parents;
        this.childtems = childern;
    }

    public void setInflater(LayoutInflater inflater, BloodDonateActivity activity) {
        this.inflater = inflater;
        this.activity = activity;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        child = childtems.get(groupPosition);

        TextView textView = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child, null);
        }

        textView = (TextView) convertView.findViewById(R.id.textView1);
        textView.setText(child.get(childPosition));


        convertView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent;
                switch (groupPosition) {
                    case 0:
                        switch (childPosition) {
                            case 0:

                                intent = new Intent(activity, ActivityBloodDonateInfo.class);
                                intent.putExtra("eligibilty", "eligibilty");
                                activity.startActivity(intent);
                                break;
                            case 1:

                                intent = new Intent(activity, ActivityBloodDonateInfo.class);

                                intent.putExtra("detail_eligibilty", "detail_eligibilty");
                                activity.startActivity(intent);
                                break;
                            case 2:
                                intent = new Intent(activity, ActivityBloodDonateInfo.class);

                                intent.putExtra("why_should_donate_blood", "why_should_donate_blood");

                                activity.startActivity(intent);
                                break;
                            case 3:
                                intent = new Intent(activity, ActivityBloodDonateInfo.class);

                                intent.putExtra("donationprocess", "donationprocess");

                                activity.startActivity(intent);
                                break;
                            case 4:
                                intent = new Intent(activity, ActivityBloodDonateInfo.class);

                                intent.putExtra("different_type_blood_donation", "different_type_blood_donation");
                                activity.startActivity(intent);
                                break;
                        }

                        break;
                    case 1:
                        switch (childPosition) {
                            case 0:
                                intent = new Intent(activity, ActivityBloodDonateInfo.class);

                                intent.putExtra("blood_groups", "blood_groups");

                                activity.startActivity(intent);
                                break;
                            case 1:
                                intent = new Intent(activity, ActivityBloodDonateInfo.class);

                                intent.putExtra("inheritance_bloodgroups", "inheritance_bloodgroups");

                                activity.startActivity(intent);
                                break;
                            case 2:
                                intent = new Intent(activity, ActivityBloodDonateInfo.class);

                                intent.putExtra("bloodcomponents", "bloodcomponents");
                                activity.startActivity(intent);
                                break;
                        }
                        break;


                }

            }


        });

        return convertView;
    }




    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded,
                             View convertViews, ViewGroup parent) {

        if (convertViews == null) {
            convertViews = inflater.inflate(R.layout.group, null);
        }



//        convertViews.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(groupPosition==2){
//                    Log.e("hi","hi");
//                   Intent  intent = new Intent(activity, ActivityBloodDonateInfo.class);
//                    intent.putExtra("day_blood_donor", "day_blood_donor");
//                    activity.startActivity(intent);
//                }
//            }
//        });

        ((CheckedTextView) convertViews.findViewById(R.id.textView1)).setText(parentItems.get(groupPosition));

        ((CheckedTextView) convertViews.findViewById(R.id.textView1)).setChecked(true);

        ImageView imageView = (ImageView) convertViews.findViewById(R.id.image);
        if (getChildrenCount(groupPosition) == 0) {
            imageView.setVisibility(View.INVISIBLE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(isExpanded ? R.mipmap.down_arrow : R.mipmap.right_arrow);
        }


        return convertViews;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return childtems.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {

        return groupPosition;
    }

    @Override
    public int getGroupCount() {

        return parentItems.size();

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}