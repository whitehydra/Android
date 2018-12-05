package com.example.pc.mainproject;

import java.util.Calendar;

public class Note {
    private String category;
    private String currency;
    private String comment;
    private String type;
    private int value;
    private Calendar time;

    Note(String category, String currency, String comment, String type, int value, Calendar time){
        this.category = category;
        this.currency = currency;
        this.comment = comment;
        this.type = type;
        this.value = value;
        this.time = time;
    }

    public String getFullInfo(){
        return "Note: category -> " + category + "; currency -> " + currency + "; comment -> " +
            comment + "\ntype -> " + type + "; value -> " + value + "; time -> " + time.get(Calendar.YEAR) +
            "." + time.get(Calendar.MONTH) + "." + time.get(Calendar.DAY_OF_MONTH) + " " +
            time.get(Calendar.HOUR) + ":" + time.get(Calendar.MINUTE);
    }


    public String getCategory() {
        return category;
    }

    public String getCurrency() {
        return currency;
    }

    public String getComment() {
        return comment;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public Calendar getTime() {
        return time;
    }
}
