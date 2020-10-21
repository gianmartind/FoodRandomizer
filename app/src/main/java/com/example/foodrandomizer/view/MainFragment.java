package com.example.foodrandomizer.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.view.menu.MenuPresenter;
import androidx.fragment.app.Fragment;

import com.example.foodrandomizer.DBHandler;
import com.example.foodrandomizer.R;
import com.example.foodrandomizer.presenter.MainPresenter;

public class MainFragment extends Fragment implements View.OnClickListener, MainPresenter.IMain {
    FragmentListener fragmentListener;
    DBHandler db;
    Button searchButton;
    MainPresenter mainPresenter;
    public MainFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        this.db = new DBHandler(this.getActivity());
        this.mainPresenter = new MainPresenter(this, this.db);
        this.searchButton = view.findViewById(R.id.search_button);
        this.searchButton.setOnClickListener(this);
        return view;
    }


    public static MainFragment newInstance(){
        MainFragment fragment = new MainFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
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
        this.mainPresenter.createRandom();
    }

    @Override
    public void openRandomMenu(int id) {
        this.fragmentListener.changeMenuId(id);
        this.fragmentListener.changePage(6);
    }
}
