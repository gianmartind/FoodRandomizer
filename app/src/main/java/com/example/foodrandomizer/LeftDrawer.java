package com.example.foodrandomizer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class LeftDrawer extends Fragment implements AdapterView.OnItemClickListener, FragmentListener{
    LeftDrawerAdapter adapter;
    ListView fragment_left;
    LeftDrawerViewModel lvm;

    public LeftDrawer(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_drawer, container, false);

        this.fragment_left = view.findViewById(R.id.lst_left);
        this.adapter = new LeftDrawerAdapter(this.getActivity());
        this.fragment_left.setAdapter(this.adapter);
        /*for(int i=0;i<isi_fragment_left.length;i++){
            this.adapter.addLine(isi_fragment_left[i]);
        }*/


        this.lvm = new ViewModelProvider(this).get(LeftDrawerViewModel.class);
        this.lvm.getList().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.updateList(strings);
                adapter.notifyDataSetChanged();
            }
        });

        lvm.loadData();
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i,long l){
        if(i == 4){
            lvm.closeApp();
        }
    }

    @Override
    public void changePage(int page) {

    }

    @Override
    public void closeApplication() {

    }
}


