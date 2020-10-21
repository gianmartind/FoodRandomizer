package com.example.foodrandomizer.presenter;

import com.example.foodrandomizer.DBHandler;
import com.example.foodrandomizer.model.Food;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MainPresenter {
    private IMain ui;
    private DBHandler db;

    public MainPresenter(IMain ui, DBHandler db){
        this.ui = ui;
        this.db = db;
    }

    public void createRandom(){
        List<Food> foodList = this.db.getAllFoodsWithName("");
        int rand = generateRandom(0, foodList.size() - 1);
        this.ui.openRandomMenu(foodList.get(rand).getId());
    }

    public int generateRandom(int min, int max){
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
    }

    public interface IMain{
        void openRandomMenu(int id);
    }
}
