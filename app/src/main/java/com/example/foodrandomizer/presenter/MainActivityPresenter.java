package com.example.foodrandomizer.presenter;

import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.foodrandomizer.SettingsPrefSaver;

public class MainActivityPresenter {
    private SettingsPrefSaver settingsPrefSaver;

    public MainActivityPresenter(SettingsPrefSaver settingsPrefSaver){
        this.settingsPrefSaver = settingsPrefSaver;
    }

    public void loadSettings(){
        if(this.settingsPrefSaver.getDarkMode().equals("NO")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if(this.settingsPrefSaver.getDarkMode().equals("YES")){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else if(this.settingsPrefSaver.getDarkMode().equals("DEF")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
    }
}
