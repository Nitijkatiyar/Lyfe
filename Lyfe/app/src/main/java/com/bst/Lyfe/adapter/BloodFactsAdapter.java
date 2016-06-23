package com.bst.Lyfe.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bst.Lyfe.R;
import com.bst.Lyfe.activities.ActivityBloodFacts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arun on 22-06-2016.
 */
public class BloodFactsAdapter extends RecyclerView.Adapter<BloodFactsAdapter.BloodFactViewHolder> {

    ActivityBloodFacts activity;
    List<String> data;
    LayoutInflater layoutInflater;


    public BloodFactsAdapter(ActivityBloodFacts activityBloodFacts, List<String> values) {
        activity = activityBloodFacts;
        data = values;

    }


    @Override
    public BloodFactsAdapter.BloodFactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.blood_fact_listitems, null);
        BloodFactViewHolder viewholder = new BloodFactViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(BloodFactsAdapter.BloodFactViewHolder holder, int position) {


           // holder._blooddot.setText("•");
            holder._bloodText.setText(""+data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class BloodFactViewHolder extends RecyclerView.ViewHolder {

        TextView _blooddot, _bloodText;
        View view;

        public BloodFactViewHolder(View views) {
            super(views);
            view = views;
            // _blooddot = (TextView) view.findViewById(R.id.bloodDots);
            _bloodText = (TextView) view.findViewById(R.id.bloodText);


        }
    }
}
