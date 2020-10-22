package com.example.foodrandomizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.foodrandomizer.model.Food;
import com.example.foodrandomizer.model.History;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FoodRandomizer";
    private static final String TABLE_FOOD = "foods";
    private static final String TABLE_HISTORY = "history";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "description";
    private static final String KEY_BAHAN = "bahan";
    private static final String KEY_LANGKAH = "langkah";
    private static final String KEY_RESTORAN = "restoran";

    private static final String KEY_DATETIME = "datetime";
    private static final String KEY_FOODID = "foodid";

    public DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_FOODS_TABLE = "CREATE TABLE " + TABLE_FOOD + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT," + KEY_DESC + " TEXT," + KEY_BAHAN + " TEXT," + KEY_LANGKAH + " TEXT,"
                + KEY_RESTORAN + " TEXT)";

        String CREATE_HISTORY_TABLE = "CREATE TABLE " + TABLE_HISTORY + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_DATETIME + " TEXT," + KEY_FOODID + " TEXT)";

        sqLiteDatabase.execSQL(CREATE_FOODS_TABLE);
        sqLiteDatabase.execSQL(CREATE_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(sqLiteDatabase);
    }

    public void addRecord(Food item){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(KEY_DESC, item.getDesc());
        values.put(KEY_BAHAN, item.getBahan());
        values.put(KEY_LANGKAH, item.getLangkah());
        values.put(KEY_RESTORAN, item.getRestoran());

        db.insert(TABLE_FOOD, null, values);
        db.close();
    }

    public void addHistory(History item){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATETIME, item.getDatetime());
        values.put(KEY_FOODID, item.getFoodId());

        db.insert(TABLE_HISTORY, null, values);
        db.close();
    }

    public List<History> getAllHistoryWithName(String name){
        List<History> historyList = new ArrayList<History>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_FOOD + " INNER JOIN " + TABLE_HISTORY
                + " ON " + TABLE_FOOD + "." + KEY_ID + "=" + TABLE_HISTORY + "." + KEY_FOODID
                + " WHERE name LIKE '%" + name + "%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                History history = new History();
                history.setId(Integer.parseInt(cursor.getString(6)));
                history.setDatetime(cursor.getString(7));
                history.setFoodId(Integer.parseInt(cursor.getString(0)));
                history.setFoodName(cursor.getString(1));

                historyList.add(history);
            } while (cursor.moveToNext());
        }
        return historyList;
    }

    public Food getFoodWithId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FOOD, new String[] { KEY_ID, KEY_NAME, KEY_DESC, KEY_BAHAN, KEY_LANGKAH, KEY_RESTORAN }, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Food food = new Food(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3).split("\\n"), cursor.getString(4).split("\\n"), cursor.getString(5).split("\\n"));
        return food;
    }

    public List<Food> getAllFoodsWithName(String name){
        List<Food> foodList = new ArrayList<Food>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FOOD + " WHERE name LIKE '%" + name + "%'" ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
                food.setId(Integer.parseInt(cursor.getString(0)));
                food.setName(cursor.getString(1));
                food.setDesc(cursor.getString(2));
                food.setBahan(cursor.getString(3).split("\\n"));
                food.setLangkah(cursor.getString(4).split("\\n"));
                food.setRestoran(cursor.getString(5).split("\\n"));

                foodList.add(food);
            } while (cursor.moveToNext());
        }

        return foodList;
    }

    public List<Integer> getAllFoodsId(){
        List<Integer> foodList = new ArrayList<Integer>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_FOOD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                foodList.add(Integer.parseInt(cursor.getString(0)));
            } while (cursor.moveToNext());
        }

        return foodList;
    }

    public int updateRecord(Food item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, item.getName());
        values.put(KEY_DESC, item.getDesc());
        values.put(KEY_BAHAN, item.getBahan());
        values.put(KEY_LANGKAH, item.getLangkah());
        values.put(KEY_RESTORAN, item.getRestoran());

        // updating row
        return db.update(TABLE_FOOD, values, KEY_ID + " = ?",
                new String[] { String.valueOf(item.getId()) });
    }

    public void deleteRecord(Food item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOOD, KEY_ID + " = ?",
                new String[] { String.valueOf(item.getId()) });
        db.close();
    }

    public void deleteHistory(History item) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HISTORY, KEY_ID + " = ?",
                new String[] { String.valueOf(item.getId()) });
        db.close();
    }

    public void deleteAllHistory(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HISTORY, null, null);
        db.close();
    }
}
