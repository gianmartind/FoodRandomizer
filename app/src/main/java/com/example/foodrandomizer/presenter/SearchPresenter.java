package com.example.foodrandomizer.presenter;

import com.example.foodrandomizer.DBHandler;
import com.example.foodrandomizer.model.Food;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenter {
    protected List<Food> foods;
    protected SearchPresenter.ISearchPage ui;
    protected DBHandler db;

    public SearchPresenter(SearchPresenter.ISearchPage view, DBHandler db){
        this.foods = new ArrayList<>();
        this.db = db;
        this.ui = view;
    }

    public void loadData(String text){
        this.foods.clear();
        List<Food> foodList = db.getAllFoodsWithName(text);
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

    public interface ISearchPage{
        void UpdateList(List<Food> data);
        void changePage();
        void openDetails(int id2);
    }
}
