package com.example.foodrandomizer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeftDrawerViewModel extends ViewModel {
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
        this.itemList.addAll(Arrays.asList(MockItemFragmentLeft.itemListArr));
        this.item.setValue(this.itemList);
    }


}
