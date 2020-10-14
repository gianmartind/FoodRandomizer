package com.example.foodrandomizer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuPageViewModel extends ViewModel {
    private List<Food> foods;
    private MutableLiveData<List<Food>> foodList;

    public MenuPageViewModel(){
        this.foods = new ArrayList<>();
        this.foodList = new MutableLiveData<>();
    }

    public LiveData<List<Food>> getList(){
        return foodList;
    }

    public void loadData(){
        this.foods.addAll(Arrays.asList(MockFood.foodObjectArr));
        this.foodList.setValue(this.foods);
    }

    public void addNew(){

    }


}
