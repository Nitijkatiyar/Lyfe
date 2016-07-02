package com.bst.Lyfe.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.bst.Lyfe.R;
import com.bst.Lyfe.adapter.BloodFactsAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by arun on 22-06-2016.
 */
public class BloodFactsActivity extends AppCompatActivity {

    String[] values = new String[]{"Blood is the life-maintaining fluid that circulates through the body's" + " " +
            "heart, arteries, veins and capillaries.", "Blood carries to the body nourishment, electrolytes, hormones," +
            "vitamins, antibodies, heat, and oxygen.", "Blood carries away from the body waste matter and carbon dioxide.",
            "Blood fights against infection and helps heal wounds, keeping you healthy.",
            "Blood makes up about 7% of your body's weight.", "A newborn baby has about one cup of blood in his or her body.", "White blood cells are the body's primary defence against infection.", "Granulocytes, a type of white blood cell, roll along blood vessel walls to\n" +
            "search and destroy bacteria.",
            "Red blood cells carry oxygen to the body's organs and tissues.", "There are about one billion red blood cells in two to three drops of\n" +
            "blood.", "Red blood cells live about 120 days in the circulatory system. ", "Blood platelets help clotting and give those with leukemia and other\n" +
            "cancers a chance to live."};

    String[] valuesmyth = new String[]{"Being a vegetarian, means that the blood does not have enough iron and cannot be donated.",
            "Giving blood hurts.", "HIV or other infections can be contracted from donating blood." +
            "Giving blood is time consuming.",
            "There is limited blood in the body and it is unhealthy to give some away.",
            "Age is a deterrent to blood donation.", "Heavy people are healthier and have more blood give.", "Health deteriorates after donating blood.",
            "You cannot take part in sports or other physical activities after donating blood.",
            "Taking medication means that one cannot be a blood donor.", "When there is a requirement, blood can be manufactured.",
            "Being of mixed race precludes blood from being helpful.", "Blood donation can tell if one is HIV positive."};

    String[] valuesFacts = new String[]{"Vegetarians can donate blood. The iron needed is taken from body stores and once a balanced diet is maintained is replaced after donation. This usually normally takes a month or so.",
            "The pain experienced is no more than a needle prick. The slight soreness that maybe where the needle was is just a reminder of the good deed done.",
            "A clear procedure exists for taking blood from each donor. Sterility is maintained at all steps. A sterile, new needle is used for each donation and is then properly discarded. Use of sterile equipment and technique limits the chance of infection.",
            "The time taken for a single donation session is normally not more than an hour or so.", " Only about 350-450ml of blood is taken during a donation session. There is enough blood in the body to donate it without any ill effects. The body makes new blood after donation.",
            "Anyone up to the age of 60 who is fit and healthy can give blood.", " Being overweight makes people less healthy. Overweight people do not have more blood.",
            "If you are healthy prior to donation, your recovery is complete in a day or two. It is advised to rest a while after donating. Drinking enough liquids replaces the lost fluid within a couple of hours. The body produces new cells faster after a donation. All the RBCs are replaced within 3-4 days and WBCs within 3 weeks.",
            "Giving blood does not interfere with ability to perform physically. Advice to avoid heavy lifting or strenuous workouts for the rest of the day is given after the donation. You can get back on track the next day.",
            " Depending on the medication being taken, it may halt donation for a period, though in many cases it won't prevent a donation. person in charge or the nursing staff should be informed before donating.",
            "Blood is not something that can be manufactured. It can only come from healthy human beings.", "Race and caste have no bearing on eligibility being a blood donor. It is the blood type and group that is of importance.", " HIV antibodies can take months to develop after infection with the virus. Those recently infected may have a negative test result and yet be able to infect others. It is better not to donate blood if at risk of getting HIV or other infections"};

    List<String> stringList = new ArrayList<String>(Arrays.asList(values));
    List<String> stringListMyth = new ArrayList<String>(Arrays.asList(valuesmyth));
    List<String> stringListFacts = new ArrayList<String>(Arrays.asList(valuesFacts));
    RecyclerView recyclerview;
    public boolean aBoolean;
    TextView headertext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodfacts);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        headertext = (TextView) findViewById(R.id.headertext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layoutManager);

        recyclerview.setBackgroundColor(getResources().getColor(R.color.colorTextSecondry));
        Bundle bundle = getIntent().getExtras();
        BloodFactsAdapter arrayAdapter = null;
        if (bundle == null) {
            aBoolean = false;
            arrayAdapter = new BloodFactsAdapter(this, stringList, aBoolean, "saaa");
        } else if (bundle.getString("myth").equals("myth")) {
            headertext.setText("Myths vs Facts about blood donation");
            aBoolean = true;
            arrayAdapter = new BloodFactsAdapter(this, stringListMyth, stringListFacts, aBoolean, "fsffks");
        }

        recyclerview.setAdapter(arrayAdapter);

    }
}
