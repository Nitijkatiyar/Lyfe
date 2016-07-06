package com.bst.Lyfe.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bst.Lyfe.R;
import com.bst.Lyfe.activities.ActivityBloodDonateInfo;
import com.bst.Lyfe.activities.BloodFactsActivity;

import java.util.List;

/**
 * Created by arun on 22-06-2016.
 */
public class BloodFactsAdapter extends RecyclerView.Adapter<BloodFactsAdapter.BloodFactViewHolder> {

    BloodFactsActivity activity;
    List<String> data;
    LayoutInflater layoutInflater;
    List<String> datamyth;
    List<String> datafacts;
    ActivityBloodDonateInfo mactivity;
    boolean flag;
    String a;
    List<String> datablooddonateinfo;

    public BloodFactsAdapter(BloodFactsActivity activityBloodFacts, List<String> values, boolean aBooleans, String aBoolean) {
        activity = activityBloodFacts;
        data = values;
        flag = aBooleans;
        a = aBoolean;

    }


    public BloodFactsAdapter(BloodFactsActivity activityBloodFacts, List<String> valuesmyth, List<String> valuefacts, boolean aBooleans, String aBoolean) {

        activity = activityBloodFacts;
        datamyth = valuesmyth;
        datafacts = valuefacts;
        flag = aBooleans;
        a = aBoolean;

    }

    public BloodFactsAdapter(ActivityBloodDonateInfo activityBloodDonateInfo, List<String> eligibilty, String aBoolean) {
        mactivity = activityBloodDonateInfo;
        datablooddonateinfo = eligibilty;
        a = aBoolean;
    }


    @Override
    public BloodFactsAdapter.BloodFactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.blood_fact_listitems, null);
        BloodFactViewHolder viewholder = new BloodFactViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(BloodFactsAdapter.BloodFactViewHolder holder, int position) {

        if (flag == true) {

            holder._blooddot.setVisibility(View.VISIBLE);
            String myths = activity.getResources().getString(R.string.myths);
            String facts = activity.getResources().getString(R.string.facts);
            holder._blooddot.setText(Html.fromHtml(myths) + " " + datamyth.get(position));
            holder._bloodText.setText(Html.fromHtml(facts) + " " + datafacts.get(position));
        } else if (a.equalsIgnoreCase("String")) {
            holder._bloodText.setText("" + datablooddonateinfo.get(position));
        } else if (flag == false) {
            holder._bloodText.setText("* " + data.get(position));

        }


    }

    @Override
    public int getItemCount() {
        if (flag == true) {
            return datamyth.size();
        } else if (a.equalsIgnoreCase("String")) {
            return datablooddonateinfo.size();
        }
        return data.size();
    }

    public class BloodFactViewHolder extends RecyclerView.ViewHolder {

        TextView _blooddot, _bloodText;
        View view;

        public BloodFactViewHolder(View views) {
            super(views);
            view = views;
            _blooddot = (TextView) view.findViewById(R.id.bloodDots);
            _bloodText = (TextView) view.findViewById(R.id.bloodText);


        }
    }
}
