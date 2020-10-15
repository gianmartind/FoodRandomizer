package com.example.foodrandomizer;

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

    public void addNew(String title, String details){
        Food item = new Food(title, details);
        this.foods.add(item);
        this.ui.UpdateList(this.foods);
        this.ui.resetAddForm();
    }

    public interface IMenuPage{
        void UpdateList(List<Food> data);
        void resetAddForm();
    }
}
