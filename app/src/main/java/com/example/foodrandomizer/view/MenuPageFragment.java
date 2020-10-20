package com.example.foodrandomizer.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.foodrandomizer.presenter.MenuPagePresenter;
import com.example.foodrandomizer.R;
import com.example.foodrandomizer.model.Food;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MenuPageFragment extends Fragment implements View.OnClickListener, MenuPagePresenter.IMenuPage {
    private MenuPageAdapter adapter;
    private ListView menuList;
    private MenuPagePresenter menuPagePresenter;
    FragmentListener fragmentListener;
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

    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.fragmentListener = (FragmentListener) context;
        } else{
            throw new ClassCastException(context.toString() + " must implement FragmentListener");
        }
    }

    @Override
    public void onClick(View view) {
        if(view == this.fab){
            this.menuPagePresenter.addNew();
        }
    }

    @Override
    public void UpdateList(List<Food> data) {
        adapter.updateList(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void changePage() {
        fragmentListener.changePage(5);
    }

}
