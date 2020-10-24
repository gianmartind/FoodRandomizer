package com.example.foodrandomizer.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodrandomizer.DBHandler;
import com.example.foodrandomizer.R;
import com.example.foodrandomizer.model.Food;
import com.example.foodrandomizer.presenter.MenuPagePresenter;
import com.example.foodrandomizer.presenter.SearchPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends Fragment implements SearchView.OnQueryTextListener, SearchPresenter.ISearchPage, AdapterView.OnItemClickListener, View.OnClickListener {
    private DBHandler db;
    private SearchPageAdapter adapter;
    private ListView menuList;
    private SearchPresenter searchPresenter;
    private Button changeQueryButton;
    FragmentListener fragmentListener;
    private SearchView search;

    public SearchPage(){};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_page, container, false);
        this.menuList = view.findViewById(R.id.list_makanan_pencarian);

        this.db = new DBHandler(this.getActivity());
        this.searchPresenter = new SearchPresenter(this, this.db);

        this.adapter = new SearchPageAdapter(this.getActivity());
        this.menuList.setAdapter(this.adapter);
        this.menuList.setOnItemClickListener(this);
        this.search = view.findViewById(R.id.pencarian);
        this.search.setOnQueryTextListener(this);
        this.changeQueryButton = view.findViewById(R.id.query_switch);
        this.changeQueryButton.setOnClickListener(this);
        searchPresenter.loadData("");

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
    public void UpdateList(List<Food> data) {
        adapter.updateList(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void changePage() {
        fragmentListener.changePage(1);
    }

    @Override
    public void changeQuery(String query) {
        if(query.equals("NAME")){
            this.search.setQueryHint(this.getActivity().getResources().getString(R.string.pencarian_menu));
        }
        else {
            this.search.setQueryHint(this.getActivity().getResources().getString(R.string.pencarian_bahan));
        }
    }

    @Override
    public void openDetails(int id2) {
        this.fragmentListener.changeMenuId(id2);
        this.fragmentListener.changePage(6);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        this.searchPresenter.openDetails(i);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        this.searchPresenter.loadData(s);
        return false;
    }

    @Override
    public void onClick(View view) {
        this.searchPresenter.queryChange();
    }
}
