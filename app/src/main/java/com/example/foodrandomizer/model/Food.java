package com.example.foodrandomizer.model;

public class Food {
    private String name;
    private String desc;

    public Food(String name, String desc){
        this.name = name;
        this.desc = desc;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
