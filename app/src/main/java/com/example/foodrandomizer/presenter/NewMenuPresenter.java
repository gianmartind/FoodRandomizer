package com.example.foodrandomizer.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.foodrandomizer.DBHandler;
import com.example.foodrandomizer.model.Food;
import com.example.foodrandomizer.view.NewMenuFragment;

public class NewMenuPresenter {
    private INewMenu ui;
    private DBHandler db;
    private Toast toast;

    public NewMenuPresenter(INewMenu ui, DBHandler db){
        this.ui = ui;
        this.db = db;
    }

    public void addNewMenu(String name, String desc, String bahan, String langkah, String restoran, Context context){
        Food item = new Food(0, name, desc, bahan.split("\\n"), langkah.split("\\n"), restoran.split("\\n"));
        this.db.addRecord(item);
        ui.clearForm();
        this.toast = Toast.makeText(context, "New Menu Added", Toast.LENGTH_SHORT);
        this.toast.show();
        ui.changePage();
    }

    public interface INewMenu{
        void changePage();
        void clearForm();
    }
}
