package com.example.foodrandomizer.presenter;

import com.example.foodrandomizer.MockFood;
import com.example.foodrandomizer.model.Food;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuPagePresenter {
    protected List<Food> foods;
    protected IMenuPage ui;


    public MenuPagePresenter(IMenuPage view){
        this.foods = new ArrayList<>();
        this.ui = view;
    }

    public void loadData(){
        this.foods.addAll(Arrays.asList(MockFood.foodObjectArr));
        this.ui.UpdateList(this.foods);
    }

    public void addNew(){
        ui.changePage();
    }

    public interface IMenuPage{
        void UpdateList(List<Food> data);
        void changePage();
    }
}
