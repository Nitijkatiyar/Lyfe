package com.bst.Lyfe.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bst.Lyfe.R;
import com.bst.Lyfe.activities.MainActivity;


public class DonateFragment extends Fragment {

    View rootView;
    LinearLayout linearLayout;
    MainActivity activity;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_donateblood, container, false);
        activity = (MainActivity) getActivity();
//        activity.toolbar.setTitle("Donate");

        linearLayout = (LinearLayout) rootView.findViewById(R.id.linearLayout);

        for (int i = 0; i < 10; i++) {
            View view = inflater.inflate(R.layout.listitem_donateblood, null);
            linearLayout.addView(view);
        }


        return rootView;
    }

}