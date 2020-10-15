package com.example.foodrandomizer.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.foodrandomizer.R;

public class MainFragment extends Fragment implements View.OnClickListener {
    Button searchButton;
    public MainFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        this.searchButton = view.findViewById(R.id.search_button);
        return view;
    }


    public static MainFragment newInstance(){
        MainFragment fragment = new MainFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    /*
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.fragmentListener = (FragmentListener) context;
        } else{
            throw new ClassCastException(context.toString() + " must implement FragmentListener");
        }
    }
     */

    @Override
    public void onClick(View view) {

    }
}
