package com.example.foodrandomizer.presenter;

import com.example.foodrandomizer.DBHandler;
import com.example.foodrandomizer.model.Food;

import java.util.ArrayList;
import java.util.List;

public class SearchPresenter {
    protected List<Food> foods;
    protected SearchPresenter.ISearchPage ui;
    protected DBHandler db;
    protected String queryBy;

    public SearchPresenter(SearchPresenter.ISearchPage view, DBHandler db){
        this.foods = new ArrayList<>();
        this.db = db;
        this.ui = view;
        this.queryBy = "NAME";
    }

    public void loadData(String text){
        this.foods.clear();
        List<Food> foodList = this.queryBy.equals("NAME") ? db.getAllFoodsWithName(text) : db.getAllFoodsWithBahan(text);
        this.foods.addAll(foodList);
        this.ui.UpdateList(this.foods);
    }

    public void openDetails(int id){
        int id2 = this.foods.get(id).getId();
        this.ui.openDetails(id2);
    }

    public void queryChange() {
        if(this.queryBy.equals("NAME")){
            this.queryBy = "BAHAN";
            this.ui.changeQuery("BAHAN");
        }
        else{
            this.queryBy = "NAME";
            this.ui.changeQuery("NAME");
        }
    }

    public interface ISearchPage{
        void UpdateList(List<Food> data);
        void changePage();
        void changeQuery(String query);
        void openDetails(int id2);
    }
}
