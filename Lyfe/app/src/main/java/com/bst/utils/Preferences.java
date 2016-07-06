package com.bst.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author iRESLab
 */
public class Preferences {


    static SharedPreferences lyfe_preference;


    public static final String MOBILE_PIN = "pin";
    public static final String MOBILE_PIN_ACTIVATED = "pinactivated";
    public static final String KEY_DEFAULT_PREFERENCE = "com.bst.lyfe";


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
}
