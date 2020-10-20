package com.example.foodrandomizer.presenter;

import android.content.Context;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.foodrandomizer.SettingsPrefSaver;

public class SettingsPagePresenter {
    public ISettingsPage ui;
    public SettingsPrefSaver settingsPrefSaver;

    public SettingsPagePresenter(ISettingsPage ui, Context context){
        this.ui = ui;
        this.settingsPrefSaver = new SettingsPrefSaver(context);
    }

    public void loadSettings(){
        if(this.settingsPrefSaver.getDarkMode().equals("NO")){
            this.ui.setDarkModeButton(0);
        } else if(this.settingsPrefSaver.getDarkMode().equals("YES")){
            this.ui.setDarkModeButton(1);
        } else if(this.settingsPrefSaver.getDarkMode().equals("DEF")){
            this.ui.setDarkModeButton(2);
        }
    }

    public void saveSettings(int darkMode){
        if(darkMode == 0){
            this.settingsPrefSaver.saveDarkMode("NO");
            //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            this.ui.loadSettings();
            this.ui.changePage();
        } else if(darkMode == 1){
            this.settingsPrefSaver.saveDarkMode("YES");
            //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            this.ui.loadSettings();
            this.ui.changePage();
        } else if(darkMode == 2){
            this.settingsPrefSaver.saveDarkMode("DEF");
            //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            this.ui.loadSettings();
            this.ui.changePage();
        }

    }

    public interface ISettingsPage{
        void setDarkModeButton(int i);
        void changePage();
        void loadSettings();
    }
}
