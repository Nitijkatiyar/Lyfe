package com.bst.Lyfe.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bst.Lyfe.R;
import com.bst.networkutils.JsonParsingException;
import com.bst.networkutils.NetworkAsyncTask;
import com.bst.networkutils.NetworkCallBack;
import com.bst.networkutils.NetworkException;
import com.bst.networkutils.ThreadTaskIds;
import com.bst.utils.ActivityChanger;
import com.bst.utils.FlawkItProgressBar;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SignUpBasicInformationActivity extends AppCompatActivity implements NetworkCallBack {


    ActivityChanger activityChanger;

    EditText _firstName, _LastName, _emailId;
    Spinner bloodGroup, _bloodDonationCycle;
    TextView _DOB, _addressCity;

    private Calendar calendar;
    private int year, month, day;

    List<String> bloodgroups;
    List<String> bloodDonationCycle;
    ProgressDialog progressDialog;
    FlawkItProgressBar progressBar;
    int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;
    Button _submit;
    LatLng location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_basicinformation);


        activityChanger = new ActivityChanger(SignUpBasicInformationActivity.this);
        progressBar = FlawkItProgressBar.getInstance();


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        _submit = (Button) findViewById(R.id.buttonSubmit);
        _submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityChanger.startActivity(new Intent(SignUpBasicInformationActivity.this, SignUpPasswordActivity.class));
            }
        });

        _firstName = (EditText) findViewById(R.id.edittextFirstName);
        _LastName = (EditText) findViewById(R.id.edittextLastName);
        _emailId = (EditText) findViewById(R.id.edittextEmailId);

        _DOB = (TextView) findViewById(R.id.textviewDOB);
        _addressCity = (TextView) findViewById(R.id.textviewAddress);

        _addressCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(SignUpBasicInformationActivity.this);
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                } catch (GooglePlayServicesNotAvailableException e) {
                }

            }
        });


        bloodgroups = new ArrayList<>();
        bloodGroup = (Spinner) findViewById(R.id.spinnerBloodGroup);
        bloodgroups.add("Select Blood group");
        bloodgroups.add("A+");
        bloodgroups.add("A-");
        bloodgroups.add("B+");
        bloodgroups.add("B-");
        bloodgroups.add("AB+");
        bloodgroups.add("AB-");
        bloodgroups.add("O+");
        bloodgroups.add("O-");
        ArrayAdapter<String> bloodgroupsAdapter = new ArrayAdapter<String>(this, R.layout.spinner_layout_request, bloodgroups);
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
        bloodDonationCycle = new ArrayList<>();
        _bloodDonationCycle = (Spinner) findViewById(R.id.spinnerBloodDonationCycle);
        bloodDonationCycle.add("Select Blood donation cycle");
        bloodDonationCycle.add("After 1 month");
        bloodDonationCycle.add("After 2 month");
        bloodDonationCycle.add("After 3 month");
        ArrayAdapter<String> donationcycle = new ArrayAdapter<String>(this, R.layout.spinner_layout_request, bloodDonationCycle);
        donationcycle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _bloodDonationCycle.setAdapter(donationcycle);
        _bloodDonationCycle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        _DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(_DOB);
            }
        });

        NetworkAsyncTask networkAsyncTask = NetworkAsyncTask.getInstance();
        try {
            networkAsyncTask.startNetworkCall(ThreadTaskIds.GET_COUNTRIES, SignUpBasicInformationActivity.this);
        } catch (NetworkException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i("Place:", " " + place.getName());
                _addressCity.setText(place.getName());
                location = place.getLatLng();
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.i("Place:", " " + status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_next, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            DatePickerDialog dialog = new DatePickerDialog(this, myDateListener, year, month, day);
            dialog.getDatePicker().setMaxDate(new Date().getTime());
            return dialog;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            year = arg1;
            month = arg2;
            day = arg3;
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        _DOB.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


    @Override
    public void beforeNetworkCall(int taskId) {

        progressBar.showProgressBar(SignUpBasicInformationActivity.this);
    }

    @Override
    public Object afterNetworkCall(int taskId, Object o) {
        progressBar.hideProgressBar(SignUpBasicInformationActivity.this);
        return null;
    }

    @Override
    public Object onNetworkCall(int taskId, Object o) throws JsonParsingException, JsonParsingException {

        return null;
    }


    @Override
    public Object onNetworkError(int taskId, NetworkException o) {
        return null;
    }


}
