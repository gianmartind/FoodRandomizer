package com.example.foodrandomizer;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.foodrandomizer.presenter.SettingsPagePresenter;

public class SettingsPrefSaver {
    protected SharedPreferences sharedPreferences;
    protected final static String SHARED_PREF_NAME = "com.example.foodrandomizer.sharedprefs";
    protected final static String KEY_DARKMODE = "DARKMODE";

    public SettingsPrefSaver(Context context){
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveDarkMode(String mode){
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString(KEY_DARKMODE, mode);
        editor.commit();
    }

    public String getDarkMode(){
        return this.sharedPreferences.getString(KEY_DARKMODE, "");
    }
}
