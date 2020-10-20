package com.example.foodrandomizer.presenter;

import com.example.foodrandomizer.DBHandler;
import com.example.foodrandomizer.MockFood;
import com.example.foodrandomizer.model.Food;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuPagePresenter {
    protected List<Food> foods;
    protected IMenuPage ui;
    protected DBHandler db;

    public MenuPagePresenter(IMenuPage view, DBHandler db){
        this.foods = new ArrayList<>();
        this.db = db;
        this.ui = view;
    }

    public void loadData(){
        List<Food> foodList = db.getAllFoodsWithName("");
        this.foods.addAll(foodList);
        this.ui.UpdateList(this.foods);
    }

    public void openDetails(int id){
        int id2 = this.foods.get(id).getId();
        this.ui.openDetails(id2);
    }

    public void addNew(){
        ui.changePage();
    }

    public interface IMenuPage{
        void UpdateList(List<Food> data);
        void changePage();
        void openDetails(int id2);
    }
}
