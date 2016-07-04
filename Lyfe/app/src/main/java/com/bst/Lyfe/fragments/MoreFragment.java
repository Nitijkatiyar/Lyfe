package com.bst.Lyfe.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bst.Lyfe.R;
import com.bst.Lyfe.activities.BloodDonateActivity;
import com.bst.Lyfe.activities.BloodFactsActivity;
import com.bst.Lyfe.activities.MainActivity;
import com.bst.Lyfe.activities.SettingActivity;
import com.bst.utils.ActivityChanger;


public class MoreFragment extends Fragment {

    RelativeLayout _bloodFactsText, donateblood, myths,settings;
    MainActivity mainActivity;
    ActivityChanger activityChanger;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        activityChanger = new ActivityChanger(mainActivity);

        _bloodFactsText = (RelativeLayout) view.findViewById(R.id.bloodFactsText);
        donateblood = (RelativeLayout) view.findViewById(R.id.donateblood);
        myths = (RelativeLayout) view.findViewById(R.id.myths);
        //settings=(RelativeLayout) view.findViewById(R.id.settings);
        donateblood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityChanger.startActivity(new Intent(mainActivity, BloodDonateActivity.class));


            }
        });
        _bloodFactsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityChanger.startActivity(new Intent(mainActivity, BloodFactsActivity.class));
            }
        });

        myths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle mBundle = new Bundle();
                mBundle.putString("myth", "myth");
                activityChanger.startActivity(new Intent(mainActivity, BloodFactsActivity.class).putExtras(mBundle));


            }
        });
        donateblood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityChanger.startActivity(new Intent(mainActivity, SettingActivity.class));
            }
        });
        return view;
    }

}