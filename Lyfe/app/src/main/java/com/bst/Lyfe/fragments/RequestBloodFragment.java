package com.bst.Lyfe.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.bst.Lyfe.R;
import com.bst.Lyfe.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;


public class RequestBloodFragment extends android.support.v4.app.Fragment {
    Spinner bloodGroup, bloodUnits;
    List<String> bloodgroups;
    List<String> bloodunits;
    MainActivity mainActivity;
    Button _submit;
    EditText name, email, mobile, location;

    public RequestBloodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_requestblood, container, false);
        _submit = (Button) view.findViewById(R.id.submit);
        name = (EditText) view.findViewById(R.id.name);
        location = (EditText) view.findViewById(R.id.location);
        email = (EditText) view.findViewById(R.id.email);
        mobile = (EditText) view.findViewById(R.id.edittextMobileNumber);


        bloodgroups = new ArrayList<>();
        bloodGroup = (Spinner) view.findViewById(R.id.spinnerBloodGroup);
        bloodgroups.add("Select Blood group");
        bloodgroups.add("A+");
        bloodgroups.add("A-");
        bloodgroups.add("B+");
        bloodgroups.add("B-");
        bloodgroups.add("AB+");
        bloodgroups.add("AB-");
        bloodgroups.add("O+");
        bloodgroups.add("O-");
        ArrayAdapter<String> bloodgroupsAdapter = new ArrayAdapter<String>(mainActivity, R.layout.spinner_layout_request, bloodgroups);
        bloodgroupsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroup.setAdapter(bloodgroupsAdapter);
        bloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        bloodunits = new ArrayList<>();
        bloodUnits = (Spinner) view.findViewById(R.id.spinnerBloodUnits);
        bloodunits.add("Required blood units");
        for (int i = 1; i <= 10; i++) {
            bloodunits.add("" + i);
        }

        ArrayAdapter<String> bloodunitsAdapter = new ArrayAdapter<String>(mainActivity, R.layout.spinner_layout_request, bloodunits);
        bloodunitsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodUnits.setAdapter(bloodunitsAdapter);
        bloodUnits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        _submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().length() > 0 && email.getText().toString().trim().length() > 0 && mobile.getText().toString().trim().length() > 0 && bloodGroup.getSelectedItemPosition() > 1 && location.getText().toString().trim().length() > 0) {
                    if (mobile.getText().toString().trim().length() == 10) {

                        Snackbar.make(v, "Request Submitted", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {

                        Snackbar.make(v, "Enter 10 digit mobile number", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } else {
                    Snackbar.make(v, "Please fill in all details", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        });


        return view;
    }

}