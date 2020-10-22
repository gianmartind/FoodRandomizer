package com.example.foodrandomizer.presenter;

import com.example.foodrandomizer.DBHandler;
import com.example.foodrandomizer.model.Food;
import com.example.foodrandomizer.model.History;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
        List<Integer> foodList = this.db.getAllFoodsId();
        int rand = 0;
        if(foodList.size() != 0){
            rand = generateRandom(0, foodList.size() - 1);
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);
            History history = new History(0, foodList.get(rand), strDate, "");
            this.db.addHistory(history);
            this.ui.openRandomMenu(foodList.get(rand));
        }
    }

    public int generateRandom(int min, int max){
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        return randomNum;
    }

    public interface IMain{
        void openRandomMenu(int id);
    }
}
