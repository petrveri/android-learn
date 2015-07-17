package com.et4334.appofpetr.api.storage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by et4334 on 7/15/15.
 */
public class PreferencesHelper {

    private Activity activity;

    private static final String fileName = "prefs";

    public PreferencesHelper(Activity activity) {
        this.activity = activity;
    }

    private SharedPreferences getSP() {
        return activity.getSharedPreferences(fileName, Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
    }

    public String getPreference(String key) {
        return getSP().getString(key, "*");
    }

    public void putPreference(String key, String value) {
        SharedPreferences.Editor editor = getSP().edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void increaseCount(String key) {
        SharedPreferences sp = getSP();
        int value = sp.getInt(key, 0) + 1;
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getCount(String key) {
        return getSP().getInt(key, 0);
    }
}
