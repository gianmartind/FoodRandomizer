package com.example.foodrandomizer.view;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.foodrandomizer.R;
import com.example.foodrandomizer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements FragmentListener {
    ActivityMainBinding bind;
    MainFragment mainFragment;
    MenuPageFragment menuPageFragment;
    FragmentManager fragmentManager;

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

        this.fragmentManager = this.getSupportFragmentManager();

        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(R.id.fragment_container, this.mainFragment).addToBackStack(null).commit();
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
        }
        ft.commit();
        this.bind.drawerLayout.closeDrawers();
    }

    @Override
    public void closeApplication(){
        this.moveTaskToBack(true);
        this.finish();
    }
}