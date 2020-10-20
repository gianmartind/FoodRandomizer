package com.example.foodrandomizer.view;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.foodrandomizer.R;
import com.example.foodrandomizer.SettingsPrefSaver;
import com.example.foodrandomizer.databinding.ActivityMainBinding;
import com.example.foodrandomizer.presenter.MainActivityPresenter;
import com.example.foodrandomizer.presenter.NewMenuPresenter;

public class MainActivity extends AppCompatActivity implements FragmentListener {
    ActivityMainBinding bind;
    MainActivityPresenter mainActivityPresenter;
    MainFragment mainFragment;
    MenuPageFragment menuPageFragment;
    FragmentManager fragmentManager;
    NewMenuFragment newMenuFragment;
    SettingsFragment settingsFragment;
    SettingsPrefSaver settingsPrefSaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bind = ActivityMainBinding.inflate(getLayoutInflater());
        View view = this.bind.getRoot();
        setContentView(view);

        this.setSupportActionBar(this.bind.toolbar);

        ActionBarDrawerToggle abdt = new ActionBarDrawerToggle(this, this.bind.drawerLayout, this.bind.toolbar, R.string.openDrawer, R.string.closeDrawer);
        this.bind.drawerLayout.addDrawerListener(abdt);
        abdt.syncState();

        this.mainFragment = new MainFragment();
        this.menuPageFragment = new MenuPageFragment();
        this.newMenuFragment = new NewMenuFragment();
        this.settingsFragment = new SettingsFragment();

        this.fragmentManager = this.getSupportFragmentManager();
        this.settingsPrefSaver = new SettingsPrefSaver(this);
        this.mainActivityPresenter = new MainActivityPresenter(this.settingsPrefSaver);
        //FragmentTransaction ft = this.fragmentManager.beginTransaction();
        //ft.add(R.id.fragment_container, this.mainFragment).addToBackStack(null).commit();

        this.mainActivityPresenter.loadSettings();
        changePage(0);
        //FragmentTransaction ft2 = this.fragmentManager.beginTransaction();
        //ft2.add(R.id.fragment_container, this.menuPageFragment).addToBackStack(null).commit();
    }

    @Override
    public void changePage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if(page == 0){
            ft.replace(R.id.fragment_container, this.mainFragment).addToBackStack(null);
        } else if(page == 2){
            ft.replace(R.id.fragment_container, this.menuPageFragment).addToBackStack(null);
        } else if(page == 3){
            ft.replace(R.id.fragment_container, this.settingsFragment).addToBackStack(null);
            /*
            int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if(nightModeFlags == Configuration.UI_MODE_NIGHT_YES){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            */
        } else if(page == 5){
            ft.replace(R.id.fragment_container, this.newMenuFragment).addToBackStack(null);
        }
        ft.commit();
        this.bind.drawerLayout.closeDrawers();
    }

    @Override
    public void loadSettings() {
        this.mainActivityPresenter.loadSettings();
    }

    @Override
    public void closeApplication(){
        this.moveTaskToBack(true);
        this.finish();
    }
}