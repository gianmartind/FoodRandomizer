package com.example.foodrandomizer.presenter;

import android.util.Log;

import com.example.foodrandomizer.DBHandler;
import com.example.foodrandomizer.model.Food;
import com.example.foodrandomizer.view.NewMenuFragment;

public class NewMenuPresenter {
    private INewMenu ui;
    private DBHandler db;

    public NewMenuPresenter(INewMenu ui, DBHandler db){
        this.ui = ui;
        this.db = db;
    }

    public void addNewMenu(String name, String desc, String bahan, String langkah, String restoran){
        Food item = new Food(0, name, desc, bahan.split("\\n"), langkah.split("\\n"), restoran.split("\\n"));
        this.db.addRecord(item);
        ui.clearForm();
        ui.changePage();
    }

    public interface INewMenu{
        void changePage();
        void clearForm();
    }
}
