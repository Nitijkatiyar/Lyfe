package com.bst.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author iRESLab
 */
public class Preferences {


    static SharedPreferences lyfe_preference;


    public static final String KEY_DEFAULT_PREFERENCE = "com.bst.lyfe";

    public static final String MOBILE_PIN = "pin";
    public static final String MOBILE_PIN_ACTIVATED = "pinactivated";
    public static final String USER_ID = "userid";
    public static final String NAME = "name";
    public static final String USER_NAME = "username";
    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastname";
    public static final String BIRTHDAY = "birthday";
    public static final String GENDER = "gender";
    public static final String EMAIL_ID = "emailid";


    public static SharedPreferences getDefaultPref(Context context) {
        if (lyfe_preference == null) {
            lyfe_preference = context.getSharedPreferences(
                    KEY_DEFAULT_PREFERENCE, Context.MODE_PRIVATE);
        }
        return lyfe_preference;
    }

    public static void clearpreferences(Context context) {
        SharedPreferences.Editor editor = getDefaultPref(context).edit();
        editor.clear();
        editor.commit();
    }

    public static String getMobilePin(Context context) {
        return getDefaultPref(context).getString(
                MOBILE_PIN, "000000");
    }

    public static void setMobilePin(Context context, String language) {
        SharedPreferences.Editor editor = getDefaultPref(context).edit();
        editor.putString(MOBILE_PIN, language);
        editor.commit();

    }

    public static boolean isMobilePinActivated(Context context) {
        return getDefaultPref(context).getBoolean(
                MOBILE_PIN, false);
    }

    public static void setMobilePinActivated(Context context, boolean language) {
        SharedPreferences.Editor editor = getDefaultPref(context).edit();
        editor.putBoolean(MOBILE_PIN, language);
        editor.commit();

    }

    public static String getEnailId(Context context) {
        return getDefaultPref(context).getString(
                EMAIL_ID, "000000");
    }

    public static void setEnailId(Context context, String language) {
        SharedPreferences.Editor editor = getDefaultPref(context).edit();
        editor.putString(EMAIL_ID, language);
        editor.commit();
    }

    public static String getUserId(Context context) {
        return getDefaultPref(context).getString(
                USER_ID, "000000");
    }

    public static void setUserId(Context context, String language) {
        SharedPreferences.Editor editor = getDefaultPref(context).edit();
        editor.putString(USER_ID, language);
        editor.commit();
    }

    public static String getName(Context context) {
        return getDefaultPref(context).getString(
                NAME, "000000");
    }

    public static void setName(Context context, String language) {
        SharedPreferences.Editor editor = getDefaultPref(context).edit();
        editor.putString(NAME, language);
        editor.commit();
    }

    public static String getUserName(Context context) {
        return getDefaultPref(context).getString(
                USER_NAME, "000000");
    }

    public static void setUserName(Context context, String language) {
        SharedPreferences.Editor editor = getDefaultPref(context).edit();
        editor.putString(USER_NAME, language);
        editor.commit();
    }


    public static String getFirstName(Context context) {
        return getDefaultPref(context).getString(
                FIRST_NAME, "000000");
    }

    public static void setFirstName(Context context, String language) {
        SharedPreferences.Editor editor = getDefaultPref(context).edit();
        editor.putString(FIRST_NAME, language);
        editor.commit();
    }

    public static String getLastName(Context context) {
        return getDefaultPref(context).getString(
                LAST_NAME, "000000");
    }

    public static void setLastName(Context context, String language) {
        SharedPreferences.Editor editor = getDefaultPref(context).edit();
        editor.putString(LAST_NAME, language);
        editor.commit();
    }

    public static String getBirthday(Context context) {
        return getDefaultPref(context).getString(
                BIRTHDAY, "000000");
    }

    public static void setBirthday(Context context, String language) {
        SharedPreferences.Editor editor = getDefaultPref(context).edit();
        editor.putString(BIRTHDAY, language);
        editor.commit();
    }

    public static String getGender(Context context) {
        return getDefaultPref(context).getString(
                GENDER, "000000");
    }

    public static void setGender(Context context, String language) {
        SharedPreferences.Editor editor = getDefaultPref(context).edit();
        editor.putString(GENDER, language);
        editor.commit();
    }
}
