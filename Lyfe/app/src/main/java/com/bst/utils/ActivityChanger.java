package com.bst.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by nitij on 26-05-2016.
 */

public class ActivityChanger {
    Activity activity;

    public ActivityChanger(Activity activity) {
        this.activity = activity;
    }


    public void startActivity(Intent intent) {
        activity.startActivity(intent);

    }
}
