package com.example.foodrandomizer.presenter;

import android.util.Log;

import com.example.foodrandomizer.view.NewMenuFragment;

public class NewMenuPresenter {
    private INewMenu ui;

    public NewMenuPresenter(INewMenu ui){
        this.ui = ui;
    }

    public void addNewMenu(String title, String desc){
        Log.d("TAG", "addNewMenu: ADDED");
    }

    public interface INewMenu{
        void changePage();
    }
}
