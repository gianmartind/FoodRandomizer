package com.example.foodrandomizer.presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

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
    protected Toast toast;

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
        this.toast.show();
    }

    public void openClear(Context context){
        this.toast = Toast.makeText(context, "History cleared", Toast.LENGTH_SHORT);
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
        History item = new History(itemId, "", "");
        this.db.deleteHistory(item);
        loadData("");
        toast.show();
    }

    public void openDeleteItem(Context context, final int itemId){
        this.toast = Toast.makeText(context, "History deleted", Toast.LENGTH_SHORT);
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
