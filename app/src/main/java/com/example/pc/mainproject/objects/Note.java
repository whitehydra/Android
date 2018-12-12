package com.example.pc.mainproject.objects;

import android.annotation.SuppressLint;
import android.content.ContentValues;

import com.example.pc.mainproject.DBhelper;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Note implements Serializable{
    private String category;
    private String currency;
    private String comment;
    private String type;
    private float value;
    private Calendar time;

    public Note(){
        this(1,Calendar.getInstance(),"","","","");
    }


    public Note(float value, Calendar time, String comment,  String type, String category, String currency ){
        this.category = category;
        this.currency = currency;
        this.comment = comment;
        this.type = type;
        this.value = value;
        this.time = time;
    }

    public Note(float value, String time, String comment,  String type, String category, String currency ){
        Calendar cl = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm");
        try {
            cl.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.category = category;
        this.currency = currency;
        this.comment = comment;
        this.type = type;
        this.value = value;
        this.time = cl;
    }


    public String getFullInfo(){
        return "Note: category -> " + category + "; currency -> " + currency + "; comment -> " +
            comment + "\ntype -> " + type + "; value -> " + value + "; time -> " + time.get(Calendar.YEAR) +
            "." + time.get(Calendar.MONTH) + "." + time.get(Calendar.DAY_OF_MONTH) + " " +
            time.get(Calendar.HOUR) + ":" + time.get(Calendar.MINUTE);
    }

    public ContentValues getContentValues(int vKey, int cKey){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm");
        ContentValues cv = new ContentValues();

        cv.put(DBhelper.NOTE_VALUE, value);
        cv.put(DBhelper.NOTE_COMMENT, comment);
        cv.put(DBhelper.NOTE_DATE, sdf.format(time.getTime()));
        cv.put(DBhelper.NOTE_TYPE, type);
        cv.put(DBhelper.NOTE_CATEGORY, cKey);
        cv.put(DBhelper.NOTE_CURRENCY, vKey);
        return cv;
    }

    public void convertValue(float curse){
        value *= curse;
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

    public float getValue() {
        return value;
    }

    public Calendar getTime() {
        return time;
    }

}
