package com.example.foodrandomizer.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodrandomizer.DBHandler;
import com.example.foodrandomizer.R;
import com.example.foodrandomizer.model.History;
import com.example.foodrandomizer.presenter.HistoryPresenter;

import java.util.List;

public class HistoryFragment extends Fragment implements SearchView.OnQueryTextListener, HistoryPresenter.IHistoryPage, View.OnClickListener, AdapterView.OnItemClickListener {
    FragmentListener fragmentListener;
    private ListView historyList;
    private HistoryAdapter adapter;
    private HistoryPresenter historyPresenter;
    private DBHandler db;
    private SearchView search;
    private ImageButton clear;

    public HistoryFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment, container, false);

        this.db = new DBHandler(this.getActivity());
        this.historyPresenter = new HistoryPresenter(this, db);
        this.adapter = new HistoryAdapter(this.getActivity());
        this.historyList = view.findViewById(R.id.list_history);
        this.historyList.setAdapter(adapter);
        this.search = view.findViewById(R.id.pencarian);
        this.search.setOnQueryTextListener(this);
        this.clear = view.findViewById(R.id.clear);
        this.clear.setOnClickListener(this);
        this.historyList.setOnItemClickListener(this);
        this.historyPresenter.loadData("");
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
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        this.historyPresenter.loadData(s);
        return false;
    }

    @Override
    public void UpdateList(List<History> data) {
        adapter.updateList(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void changePage() {
        fragmentListener.changePage(1);
    }

    @Override
    public void onClick(View view) {
        this.historyPresenter.openClear(this.getActivity());
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        this.historyPresenter.openDeleteItem(this.getActivity(), i);
    }
}
