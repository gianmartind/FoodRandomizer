package com.example.foodrandomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class LeftDrawer extends Fragment {
    LeftDrawerAdapter adapter;
    ListView fragment_left;

    public LeftDrawer(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_drawer, container, false);
        String[] isi_fragment_left = { "Home", "Cari", "Menu", "Setting", "Exit"};
        this.fragment_left = view.findViewById(R.id.lst_left);
        this.adapter = new LeftDrawerAdapter(this.getActivity());
        this.fragment_left.setAdapter(this.adapter);
        for(int i=0;i<isi_fragment_left.length;i++){
            this.adapter.addLine(isi_fragment_left[i]);
        }
        return view;
    }
}


