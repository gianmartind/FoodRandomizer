package com.example.foodrandomizer;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.foodrandomizer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding bind;
    MainFragment mainFragment;
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

        this.fragmentManager = this.getSupportFragmentManager();

        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(R.id.fragment_container, this.mainFragment).addToBackStack(null).commit();

    }
}