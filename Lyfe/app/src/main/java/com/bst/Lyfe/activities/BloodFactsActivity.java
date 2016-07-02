package com.bst.Lyfe.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bst.Lyfe.R;
import com.bst.Lyfe.adapter.BloodFactsAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by arun on 22-06-2016.
 */
public class BloodFactsActivity extends AppCompatActivity {

    String[] values = new String[]{"Blood is the life-maintaining fluid that circulates through the body's" +" "+
            "heart, arteries, veins and capillaries.", "Blood carries to the body nourishment, electrolytes, hormones," +
            "vitamins, antibodies, heat, and oxygen.", "Blood carries away from the body waste matter and carbon dioxide.",
            "Blood fights against infection and helps heal wounds, keeping you healthy.",
            "Blood makes up about 7% of your body's weight.", "A newborn baby has about one cup of blood in his or her body.", "White blood cells are the body's primary defence against infection.", "Granulocytes, a type of white blood cell, roll along blood vessel walls to\n" +
            "search and destroy bacteria.",
            "Red blood cells carry oxygen to the body's organs and tissues.", "There are about one billion red blood cells in two to three drops of\n" +
            "blood.", "Red blood cells live about 120 days in the circulatory system. ", "Blood platelets help clotting and give those with leukemia and other\n" +
            "cancers a chance to live."};

    List<String> stringList = new ArrayList<String>(Arrays.asList(values));
    RecyclerView recyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodfacts);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setBackgroundColor(getResources().getColor(R.color.colorTextSecondry));
        BloodFactsAdapter arrayAdapter = new BloodFactsAdapter(this, stringList);
        recyclerview.setAdapter(arrayAdapter);

    }
}
