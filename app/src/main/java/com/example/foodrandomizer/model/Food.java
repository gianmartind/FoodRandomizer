package com.example.foodrandomizer.model;

public class Food {
    private int id;
    private String name;
    private String desc;
    private String[] bahan;
    private String[] langkah;
    private String[] restoran;

    public Food(int id, String name, String desc, String[] bahan, String[] langkah, String[] restoran){
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.bahan = bahan;
        this.langkah = langkah;
        this.restoran = restoran;
    }

    public Food() {
        //empty
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setBahan(String[] bahan) {
        this.bahan = bahan;
    }

    public String getBahan() {
        String res = "";
        for(int i = 0; i < bahan.length; i++){
            res += bahan[i] + ";";
        }
        return res;
    }

    public String[] getBahanArray(){
        return this.bahan;
    }

    public void setLangkah(String[] langkah) {
        this.langkah = langkah;
    }

    public String getLangkah() {
        String res = "";
        for(int i = 0; i < langkah.length; i++){
            res += langkah[i] + ";";
        }
        return res;
    }

    public String[] getLangkahArray(){
        return this.langkah;
    }

    public void setRestoran(String[] restoran) {
        this.restoran = restoran;
    }

    public String getRestoran() {
        String res = "";
        for(int i = 0; i < restoran.length; i++){
            res += restoran[i] + ";";
        }
        return res;
    }

    public String[] getRestoranArray(){
        return this.restoran;
    }
}
