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

public class MenuPageFragment extends Fragment implements View.OnClickListener, MenuPagePresenter.IMenuPage {
    private MenuPageAdapter adapter;
    private ListView menuList;
    private MenuPagePresenter menuPagePresenter;
    private FloatingActionButton fab;

    public MenuPageFragment(){};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_page, container, false);
        this.menuList = view.findViewById(R.id.menu_list);

        /*
        this.menuPageViewModel = new ViewModelProvider(this).get(MenuPageViewModel.class);
        this.menuPageViewModel.getList().observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foodList) {
                adapter.updateList(foodList);
                adapter.notifyDataSetChanged();
            }
        });
        */

        this.menuPagePresenter = new MenuPagePresenter(this);

        this.fab = view.findViewById(R.id.fab);
        this.fab.setOnClickListener(this);

        this.adapter = new MenuPageAdapter(this.getActivity());
        this.menuList.setAdapter(this.adapter);

        menuPagePresenter.loadData();
        //this.menuPageViewModel.loadData();

        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == this.fab){
            menuPagePresenter.addNew("Title", "Description");
        }
    }

    @Override
    public void UpdateList(List<Food> data) {
        adapter.updateList(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void resetAddForm() {

    }
}
