package com.example.jugjig.internalstorage;

import android.content.ContentValues;

public class Weight {

    private int id;
    private String date;
    private String weight;
    ContentValues _row;


    public Weight() {
        _row = new ContentValues();
    }

    public Weight(int id, String date, String weight) {
        this.id = id;
        this.date = date;
        this.weight = weight;
    }

    public ContentValues get_row() {
        return _row;
    }

    public void set_row(String date, String weight) {
        this.date = date;
        this.weight = weight;
        _row.put("date",date);
        _row.put("weight", weight);
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
