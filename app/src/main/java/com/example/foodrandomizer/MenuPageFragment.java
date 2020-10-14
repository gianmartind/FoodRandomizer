package com.example.foodrandomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class MenuPageFragment extends Fragment {
    private MenuPageAdapter adapter;
    private ListView menuList;
    public MenuPageFragment(){};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_page, container, false);
        this.adapter = new MenuPageAdapter(this);
        this.menuList.setAdapter(this.adapter);
        return view;
    }


}
