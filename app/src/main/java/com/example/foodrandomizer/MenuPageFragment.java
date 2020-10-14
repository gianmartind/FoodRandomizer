package com.example.foodrandomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MenuPageFragment extends Fragment implements View.OnClickListener {
    private MenuPageAdapter adapter;
    private ListView menuList;
    private MenuPageViewModel menuPageViewModel;
    private FloatingActionButton fab;
    public MenuPageFragment(){};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_page, container, false);
        this.menuList = view.findViewById(R.id.menu_list);

        this.menuPageViewModel = new ViewModelProvider(this).get(MenuPageViewModel.class);
        this.menuPageViewModel.getList().observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foodList) {
                adapter.updateList(foodList);
                adapter.notifyDataSetChanged();
            }
        });

        this.fab = view.findViewById(R.id.fab);
        this.fab.setOnClickListener(this);

        this.adapter = new MenuPageAdapter(this.getActivity());
        this.menuList.setAdapter(this.adapter);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.fab){
            menuPageViewModel.addNew();
        }
    }
}
