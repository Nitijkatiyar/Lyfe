package com.bst.Lyfe;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bst.utils.ActivityChanger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class SignUpBasicInformationActivity extends AppCompatActivity {


    ActivityChanger activityChanger;

    EditText _firstName, _LastName, _address, _emailId;
    Spinner _country, _state, _city, bloodGroup, _bloodDonationCycle;
    TextView _DOB;

    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_basicinformation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        activityChanger = new ActivityChanger(SignUpBasicInformationActivity.this);


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        _firstName = (EditText) findViewById(R.id.edittextFirstName);
        _LastName = (EditText) findViewById(R.id.edittextLastName);
        _address = (EditText) findViewById(R.id.edittextAddress);
        _emailId = (EditText) findViewById(R.id.edittextEmailId);

        _DOB = (TextView) findViewById(R.id.textviewDOB);

        _country = (Spinner) findViewById(R.id.spinnerCountry);
        _state = (Spinner) findViewById(R.id.spinnerState);
        _city = (Spinner) findViewById(R.id.spinnerCity);
        bloodGroup = (Spinner) findViewById(R.id.spinnerBloodGroup);
        _bloodDonationCycle = (Spinner) findViewById(R.id.spinnerBloodDonationCycle);

        _DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(_DOB);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
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


    public ArrayList<String> getAllCountries() {
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        for (Locale locale : locales) {
            String country = locale.getDisplayCountry();
            if (country.trim().length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries);
        for (String country : countries) {
            System.out.println(country);
        }

        return countries;
    }
}
