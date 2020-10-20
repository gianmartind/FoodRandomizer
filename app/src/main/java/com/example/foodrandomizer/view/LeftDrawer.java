package com.example.foodrandomizer.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.foodrandomizer.model.ItemLeftDrawer;
import com.example.foodrandomizer.presenter.LeftDrawerPresenter;
import com.example.foodrandomizer.R;

import java.util.List;

public class LeftDrawer extends Fragment implements AdapterView.OnItemClickListener, LeftDrawerPresenter.ILeftDrawer {
    LeftDrawerAdapter adapter;
    ListView fragment_left;
    LeftDrawerPresenter leftDrawerPresenter;
    FragmentListener fragmentListener;

    public LeftDrawer(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_drawer, container, false);

        this.fragment_left = view.findViewById(R.id.lst_left);
        this.leftDrawerPresenter = new LeftDrawerPresenter(this);

        this.adapter = new LeftDrawerAdapter(this.getActivity());
        this.fragment_left.setAdapter(this.adapter);
        this.fragment_left.setOnItemClickListener(this);
        /*for(int i=0;i<isi_fragment_left.length;i++){
            this.adapter.addLine(isi_fragment_left[i]);
        }*/

        /*
        this.lvm = new ViewModelProvider(this).get(LeftDrawerViewModel.class);
        this.lvm.getList().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                adapter.updateList(strings);
                adapter.notifyDataSetChanged();
            }
        });
        */
        this.leftDrawerPresenter.loadData();
        //lvm.loadData();

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
    public void onItemClick(AdapterView<?> adapterView, View view, int i,long l){
        if(i == 0){
            this.leftDrawerPresenter.changePage(0);
        }
        else if(i == 2){
            this.leftDrawerPresenter.changePage(2);
        }
        else if(i == 3){
            this.leftDrawerPresenter.changePage(3);
        }
        else if(i == 4){
            this.leftDrawerPresenter.closeApp();
        }
    }

    @Override
    public void updateList(List<ItemLeftDrawer> itemList) {
        this.adapter.updateList(itemList);
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void changePage(int i) {
        this.fragmentListener.changePage(i);
    }

    @Override
    public void closeApplication() {
        this.fragmentListener.closeApplication();
    }
}


