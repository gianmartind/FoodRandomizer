package com.example.foodrandomizer.presenter;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.foodrandomizer.DBHandler;
import com.example.foodrandomizer.model.Food;
import com.example.foodrandomizer.model.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryPresenter {
    protected List<History> histories;
    protected HistoryPresenter.IHistoryPage ui;
    protected DBHandler db;

    public HistoryPresenter(HistoryPresenter.IHistoryPage view, DBHandler db){
        this.histories = new ArrayList<>();
        this.db = db;
        this.ui = view;
    }

    public void loadData(String text){
        this.histories.clear();
        List<History> historyList = db.getAllHistoryWithName(text);
        this.histories.addAll(historyList);
        this.ui.UpdateList(this.histories);
    }

    public void clearAll(){
        this.db.deleteAllHistory();
        loadData("");
    }

    public void openClear(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Clear Data Confirmation");
        builder.setMessage("Delete all item?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                clearAll();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void deleteItem(int itemId){
        History item = new History(itemId, 0,"","");
        this.db.deleteHistory(item);
        loadData("");
    }

    public void openDeleteItem(Context context, final int itemId){
        final int historyId = this.histories.get(itemId).getId();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Item Confirmation");
        builder.setMessage("Delete this item?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                deleteItem(historyId);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void addNew(){
        ui.changePage();
    }

    public interface IHistoryPage{
        void UpdateList(List<History> data);
        void changePage();
    }
}
