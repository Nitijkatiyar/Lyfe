package com.bst.Lyfe.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bst.Lyfe.R;
import com.bst.Lyfe.activities.BloodFactsActivity;
import com.bst.Lyfe.activities.DisclaimerActivity;
import com.bst.Lyfe.activities.MainActivity;
import com.bst.utils.ActivityChanger;


public class MoreFragment extends Fragment {
    RelativeLayout _bloodFactsText, _disclaimer;
    MainActivity mainActivity;
    ActivityChanger activityChanger;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        activityChanger = new ActivityChanger(mainActivity);

        _bloodFactsText = (RelativeLayout) view.findViewById(R.id.bloodFactsText);
        _bloodFactsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityChanger.startActivity(new Intent(mainActivity, BloodFactsActivity.class));
            }
        });

        _disclaimer = (RelativeLayout) view.findViewById(R.id.disclaimer);
        _disclaimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityChanger.startActivity(new Intent(mainActivity, DisclaimerActivity.class));
            }
        });
        return view;
    }

}