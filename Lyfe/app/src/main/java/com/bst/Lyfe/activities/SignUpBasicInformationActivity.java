package com.bst.Lyfe.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bst.Lyfe.R;
import com.bst.Lyfe.models.CountryStateCityModel;
import com.bst.networkutils.JsonParsingException;
import com.bst.networkutils.NetworkAsyncTask;
import com.bst.networkutils.NetworkCallBack;
import com.bst.networkutils.NetworkException;
import com.bst.networkutils.NetworkTools;
import com.bst.networkutils.ThreadTaskIds;
import com.bst.utils.ActivityChanger;
import com.bst.utils.FlawkItProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SignUpBasicInformationActivity extends AppCompatActivity implements NetworkCallBack {


    ActivityChanger activityChanger;

    EditText _firstName, _LastName, _address, _emailId;
    Spinner _country, _state, _city, bloodGroup, _bloodDonationCycle;
    TextView _DOB;

    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    ArrayList<CountryStateCityModel> _countries;
    ArrayList<CountryStateCityModel> _states;
    ArrayList<CountryStateCityModel> _cities;
    ArrayAdapter<String> _countriesAdapter;
    ArrayAdapter<String> _stateAdapter;
    ArrayAdapter<String> _citiesAdapter;
    List<String> countrynames;
    List<String> statenames;
    List<String> citynames;
    List<String> bloodgroups;
    List<String> bloodDonationCycle;
    private int _countryCode = 0, _stateCode = 0, _cityCode = 0;
    ProgressDialog progressDialog;
    FlawkItProgressBar progressBar;
    boolean launched = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_basicinformation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activityChanger = new ActivityChanger(SignUpBasicInformationActivity.this);
        progressBar = FlawkItProgressBar.getInstance();


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        countrynames = new ArrayList<>();
        countrynames.add("Select your country");
        statenames = new ArrayList<>();
        statenames.add("Select your state");
        citynames = new ArrayList<>();
        citynames.add("Select your city");

        _firstName = (EditText) findViewById(R.id.edittextFirstName);
        _LastName = (EditText) findViewById(R.id.edittextLastName);
        _address = (EditText) findViewById(R.id.edittextAddress);
        _emailId = (EditText) findViewById(R.id.edittextEmailId);

        _DOB = (TextView) findViewById(R.id.textviewDOB);

        _country = (Spinner) findViewById(R.id.spinnerCountry);

        _state = (Spinner) findViewById(R.id.spinnerState);
        _state.setVisibility(View.GONE);

        _city = (Spinner) findViewById(R.id.spinnerCity);
        _city.setVisibility(View.GONE);

        bloodGroup = (Spinner) findViewById(R.id.spinnerBloodGroup);
        bloodgroups.add("A+");
        bloodgroups.add("A-");
        bloodgroups.add("B+");
        bloodgroups.add("B-");
        bloodgroups.add("AB+");
        bloodgroups.add("AB-");
        bloodgroups.add("O+");
        bloodgroups.add("O-");
        ArrayAdapter<String> bloodgroupsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bloodgroups);
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

        _bloodDonationCycle = (Spinner) findViewById(R.id.spinnerBloodDonationCycle);
        bloodDonationCycle.add("After 1 month");
        bloodDonationCycle.add("After 2 month");
        bloodDonationCycle.add("After 3 month");
        ArrayAdapter<String> donationcycle = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bloodDonationCycle);
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

        _country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                   if (position == 0) {
                                                       return;
                                                   }
                                                   _countryCode = _countries.get(position).getCode();
                                                   NetworkAsyncTask networkAsyncTask = NetworkAsyncTask.getInstance();
                                                   try {
                                                       networkAsyncTask.startNetworkCall(ThreadTaskIds.GET_STATES, SignUpBasicInformationActivity.this);
                                                   } catch (NetworkException e) {
                                                       e.printStackTrace();
                                                   }
                                               }

                                               @Override
                                               public void onNothingSelected(AdapterView<?> parent) {

                                               }
                                           }

        );

        _state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

                                         {
                                             @Override
                                             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                 if (position == 0) {
                                                     return;
                                                 }
                                                 _stateCode = _states.get(position).getCode();
                                                 NetworkAsyncTask networkAsyncTask = NetworkAsyncTask.getInstance();
                                                 try {
                                                     networkAsyncTask.startNetworkCall(ThreadTaskIds.GET_CITIES, SignUpBasicInformationActivity.this);
                                                 } catch (NetworkException e) {
                                                     e.printStackTrace();
                                                 }
                                             }

                                             @Override
                                             public void onNothingSelected(AdapterView<?> parent) {

                                             }
                                         }

        );

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
            activityChanger.startActivity(new Intent(SignUpBasicInformationActivity.this, SignUpPasswordActivity.class));
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
        if (taskId == ThreadTaskIds.GET_COUNTRIES) {
            String url = "http://iamrohit.in/lab/php_ajax_country_state_city_dropdown/api.php?type=getCountries";
            NetworkTools networkTools = NetworkTools.getInstance();
            if (networkTools.checkNetworkConnection(SignUpBasicInformationActivity.this) && url.length() > 0) {
                final JSONObject response = networkTools.getJsonData(SignUpBasicInformationActivity.this, url);

                if (response != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                parseJsonData(response, ThreadTaskIds.GET_COUNTRIES);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            } else {
                Toast.makeText(SignUpBasicInformationActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        }
        if (taskId == ThreadTaskIds.GET_STATES) {
            String url = "http://iamrohit.in/lab/php_ajax_country_state_city_dropdown/api.php?type=getStates&countryId=" + _countryCode;
            NetworkTools networkTools = NetworkTools.getInstance();
            if (networkTools.checkNetworkConnection(SignUpBasicInformationActivity.this) && url.length() > 0) {
                final JSONObject response = networkTools.getJsonData(SignUpBasicInformationActivity.this, url);

                if (response != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                parseJsonData(response, ThreadTaskIds.GET_STATES);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            } else {
                Toast.makeText(SignUpBasicInformationActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        }
        if (taskId == ThreadTaskIds.GET_CITIES) {
            String url = "http://iamrohit.in/lab/php_ajax_country_state_city_dropdown/api.php?type=getCities&stateId=" + _stateCode;
            NetworkTools networkTools = NetworkTools.getInstance();
            if (networkTools.checkNetworkConnection(SignUpBasicInformationActivity.this) && url.length() > 0) {
                final JSONObject response = networkTools.getJsonData(SignUpBasicInformationActivity.this, url);

                if (response != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                parseJsonData(response, ThreadTaskIds.GET_CITIES);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

            } else {
                Toast.makeText(SignUpBasicInformationActivity.this, "Please check your internet connection.", Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }

    private void parseJsonData(JSONObject response, int taskid) throws JSONException {
        if (taskid == ThreadTaskIds.GET_COUNTRIES) {
            if (response.getString("status").equalsIgnoreCase("success")) {
                JSONObject jsonObject = response.getJSONObject("result");
                jsonToMap(jsonObject, ThreadTaskIds.GET_COUNTRIES);
                sortList(_countries);

                for (int i = 0; i < _countries.size(); i++) {
                    countrynames.add(_countries.get(i).getName());
                }
                _countriesAdapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, countrynames);
                _countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                _country.setAdapter(_countriesAdapter);
            }

        }
        if (taskid == ThreadTaskIds.GET_STATES) {
            if (response.getString("status").equalsIgnoreCase("success")) {
                JSONObject jsonObject = response.getJSONObject("result");
                jsonToMap(jsonObject, ThreadTaskIds.GET_STATES);
                sortList(_states);

                for (int i = 0; i < _states.size(); i++) {
                    statenames.add(_states.get(i).getName());
                }
                if (_states.size() > 0) {
                    _stateAdapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_spinner_item, statenames);
                    _stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    _state.setAdapter(_stateAdapter);
                    _state.setVisibility(View.VISIBLE);
                }
            }
        }
        if (taskid == ThreadTaskIds.GET_CITIES) {
            if (response.getString("status").equalsIgnoreCase("success")) {
                JSONObject jsonObject = response.getJSONObject("result");
                jsonToMap(jsonObject, ThreadTaskIds.GET_CITIES);
                sortList(_cities);

                for (int i = 0; i < _cities.size(); i++) {
                    citynames.add(_cities.get(i).getName());
                }
                if (_cities.size() > 0) {
                    _citiesAdapter = new ArrayAdapter<String>(this,
                            android.R.layout.simple_spinner_item, citynames);
                    _citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    _city.setAdapter(_citiesAdapter);
                    _city.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void sortList(List<CountryStateCityModel> list) {
        Collections.sort(list, new Comparator<CountryStateCityModel>() {
            @Override
            public int compare(CountryStateCityModel s1, CountryStateCityModel s2) {
                return s1.getName().compareToIgnoreCase(s2.getName());
            }
        });
    }

    @Override
    public Object onNetworkError(int taskId, NetworkException o) {
        return null;
    }

    public void jsonToMap(JSONObject t, int type) throws JSONException {

        HashMap<String, String> map = new HashMap<String, String>();
        JSONObject jObject = t;
        Iterator<?> keys = jObject.keys();
        if (type == ThreadTaskIds.GET_COUNTRIES) {
            _countries = new ArrayList<>();
        } else if (type == ThreadTaskIds.GET_STATES) {
            _states = new ArrayList<>();
        } else if (type == ThreadTaskIds.GET_CITIES) {
            _cities = new ArrayList<>();
        }
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String value = jObject.getString(key);
            if (type == ThreadTaskIds.GET_COUNTRIES) {
                CountryStateCityModel countryStateCityModel = new CountryStateCityModel();
                countryStateCityModel.setCode(Integer.parseInt(key));
                countryStateCityModel.setName(value);
                _countries.add(countryStateCityModel);
            } else if (type == ThreadTaskIds.GET_STATES) {
                CountryStateCityModel countryStateCityModel = new CountryStateCityModel();
                countryStateCityModel.setCode(Integer.parseInt(key));
                countryStateCityModel.setName(value);
                _states.add(countryStateCityModel);
            } else if (type == ThreadTaskIds.GET_CITIES) {
                CountryStateCityModel countryStateCityModel = new CountryStateCityModel();
                countryStateCityModel.setCode(Integer.parseInt(key));
                countryStateCityModel.setName(value);
                _cities.add(countryStateCityModel);
            }

        }

    }


}
