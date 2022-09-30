package com.vaibhav_kolte.vaibhavacrobuild;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;


public class SharePref {

    public static String PREF_NAME = "UserLogin";

    public static String userId = "userId";
    public static String password = "password";
    public static String name = "name";





    // Store data in Shared Preference
    public static void storeSharedPref(Context context, String key,
                                       String value) {
        try {
            if (!TextUtils.isEmpty(key)) {
                SharedPreferences.Editor editor = context.
                        getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
                editor.putString(key, value);
                editor.apply();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Read data From Shared Preference
    public static String readSharedPref(Context context, String key) {
        try {
            if (!TextUtils.isEmpty(key)) {
                SharedPreferences preference = context.getSharedPreferences(PREF_NAME,
                        Context.MODE_PRIVATE);
                return preference.getString(key, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // Clear single share pref value
    public static String removeSharePrefValue(Context context, String key) {
        try {
            if (!TextUtils.isEmpty(key)) {
                SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME,
                        Context.MODE_PRIVATE).edit();
                editor.remove(key);
                editor.apply();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // Delete all data from Shared Preference
    public static void clearSharedPref(Context context) {
        try {
            SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME,
                    Context.MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
