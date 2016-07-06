package com.bst.Lyfe.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bst.Lyfe.R;
import com.bst.Lyfe.activities.AboutActivity;
import com.bst.Lyfe.activities.BloodDonateActivity;
import com.bst.Lyfe.activities.BloodFactsActivity;
import com.bst.Lyfe.activities.MainActivity;
import com.bst.utils.ActivityChanger;


public class MoreFragment extends Fragment {

    RelativeLayout _bloodFactsText, donateblood, myths, about;
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
        about = (RelativeLayout) view.findViewById(R.id.aboutus);

        myths = (RelativeLayout) view.findViewById(R.id.myths);
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
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityChanger.startActivity(new Intent(mainActivity, AboutActivity.class));
            }
        });

        myths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, BloodFactsActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("myth", "myth");
                intent.putExtras(mBundle);
                activityChanger.startActivity(intent);
            }
        });
        return view;
    }

}