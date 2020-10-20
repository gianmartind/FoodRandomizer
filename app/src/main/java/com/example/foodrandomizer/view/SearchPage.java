package com.example.foodrandomizer.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodrandomizer.R;
import com.example.foodrandomizer.model.Food;

import java.util.ArrayList;

public class SearchPage extends Fragment{
    private FragmentListener fragmentListener;
    ListView list;
    SearchPageAdapter adapter;
    SearchView pencarian;
    Food[] foodlist;
    ArrayList<Food> arrayList = new ArrayList<Food>();

    public SearchPage(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance){
        View view = inflater.inflate(R.layout.search_page,container,false);
        return view;
    }

    public void changePage(){
        fragmentListener.changePage(1);
    }
}
