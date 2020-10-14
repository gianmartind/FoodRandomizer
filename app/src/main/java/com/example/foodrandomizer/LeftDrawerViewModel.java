package com.example.foodrandomizer;

import android.app.Activity;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeftDrawerViewModel extends ViewModel {
    private FragmentListener fragmentListener;
    private List<String> itemList;
    private MutableLiveData<List<String>> item;

    public LeftDrawerViewModel(){
        this.itemList = new ArrayList<>();
        this.item = new MutableLiveData<>();
    }

    public LiveData<List<String>> getList(){
        return item;
    }

    public void loadData(){
        String[] isi_fragment_left = { "Home", "Cari", "Menu", "Setting", "Exit"};
        this.itemList.addAll(Arrays.asList(isi_fragment_left));
        this.item.setValue(this.itemList);
    }

    public void closeApp(){
        fragmentListener.closeApplication();
    }

}
