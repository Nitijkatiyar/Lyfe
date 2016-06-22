package com.bst.Lyfe.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bst.Lyfe.R;
import com.bst.Lyfe.activities.ActivityBloodFacts;
import com.bst.Lyfe.activities.MainActivity;


public class MoreFragment extends Fragment {
    RelativeLayout _bloodFactsText;
    MainActivity mainActivity;


    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        _bloodFactsText=(RelativeLayout)view.findViewById(R.id.bloodFactsText);
        _bloodFactsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, ActivityBloodFacts.class);
                startActivity(intent);
            }
        });
        return view;
    }

}