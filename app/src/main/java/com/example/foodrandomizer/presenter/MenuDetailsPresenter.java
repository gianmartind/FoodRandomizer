package com.example.foodrandomizer.presenter;

import com.example.foodrandomizer.DBHandler;
import com.example.foodrandomizer.model.Food;

public class MenuDetailsPresenter {
    private int id;
    private DBHandler db;
    private IMenuDetails ui;

    public MenuDetailsPresenter(int id, DBHandler db, IMenuDetails ui){
        this.id = id;
        this.db = db;
        this.ui = ui;
    }

    public void readData(){
        Food item = this.db.getFoodWithId(this.id);
        this.ui.writeData(item);
    }

    public void deleteRecord(){
        this.db.deleteRecord(this.db.getFoodWithId(this.id));
        this.ui.changePage();
    }

    public void saveRecord(String name, String desc, String bahan, String langkah, String restoran){
        Food item = new Food(this.id, name, desc, bahan.split("\\n"), langkah.split("\\n"), restoran.split("\\n"));
        this.db.updateRecord(item);
        this.ui.changePage();
    }

    public interface IMenuDetails{
        void writeData(Food item);
        void changePage();
    }
}