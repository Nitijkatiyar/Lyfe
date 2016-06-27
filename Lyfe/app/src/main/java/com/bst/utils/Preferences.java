package com.bst.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author iRESLab
 */
public class Preferences {


    static SharedPreferences lyfe_preference;

    public static final String USER_ID = "userId";
    public static final String NAME = "name";
    public static final String USER_NAME = "userName";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String BIRTHDAY = "birthday";
    public static final String GENDER = "gender";
    public static final String EMAIL_ID = "emailId";
    public static final String KEY_DEFAULT_PREFERENCE = "com.bst.lyfe";


    public static SharedPreferences getDefaultPref(Context context) {
        if (lyfe_preference == null) {
            lyfe_preference = context.getApplicationContext().getSharedPreferences(
                    KEY_DEFAULT_PREFERENCE, Context.MODE_PRIVATE);
        }
        return lyfe_preference;
    }


    public static String getPrefrence(Context context,String PrefID) {
        return getDefaultPref(context).getString(PrefID, "NO USER FOUND");
    }

    public static void setPrefrences(Context context,String PrefID, String userId) {
        SharedPreferences.Editor editor = getDefaultPref(context).edit();
        editor.putString(PrefID, userId);
        editor.commit();
    }





}
