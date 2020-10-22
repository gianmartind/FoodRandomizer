package com.example.foodrandomizer.model;

public class History {
    int id;
    String datetime;
    String foodName;

    public History(int id, String datetime, String foodName){
        this.id = id;
        this.datetime = datetime;
        this.foodName = foodName;
    }

    public History(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
