package com.jiahaoliuliu.mathcalculator.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.jiahaoliuliu.mathcalculator.MainApplication;

/**
 * Created by jiahaoliuliu on 1/10/18.
 */

public class PersistentManager {
    public enum StringKey {
        CONFIGURATION_MODEL
    }

    // Internal variable
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    // Singleton
    private static PersistentManager persistentManager;

    private PersistentManager() {
        sharedPreferences = MainApplication.getContext()
                .getSharedPreferences("Shared_prefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static PersistentManager getInstance() {
        if (persistentManager == null) {
            persistentManager = new PersistentManager();
        }

        return persistentManager;
    }

    public void set(StringKey stringKey, String value) {
        editor.putString(stringKey.toString(), value);
        editor.commit();
    }

    public String get(StringKey stringKey) {
        return sharedPreferences.getString(stringKey.toString(), "");
    }

    public boolean contains(StringKey stringKey) {
        return sharedPreferences.contains(stringKey.toString());
    }

}
