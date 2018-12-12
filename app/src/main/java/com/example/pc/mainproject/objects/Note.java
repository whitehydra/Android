package com.example.pc.mainproject.objects;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.v4.os.ConfigurationCompat;

import com.example.pc.mainproject.DBhelper;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Note implements Serializable{
    private String category;
    private String currency;
    private String comment;
    private String type;
    private int value;
    private Calendar time;

    public Note(){
        this(1,Calendar.getInstance(),"","","","");
    }


    public Note(int value, Calendar time, String comment,  String type, String category, String currency ){
        this.category = category;
        this.currency = currency;
        this.comment = comment;
        this.type = type;
        this.value = value;
        this.time = time;
    }

    public Note(int value, String time, String comment,  String type, String category, String currency ){
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

    public ContentValues getContentValues(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm");
        ContentValues cv = new ContentValues();

        cv.put(DBhelper.VALUE, value);
        cv.put(DBhelper.COMMENT, comment);
        cv.put(DBhelper.DATE, sdf.format(time.getTime()));
        cv.put(DBhelper.TYPE, type);
        cv.put(DBhelper.CATEGORY, category);
        cv.put(DBhelper.CURRENCY, currency);
        return cv;
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
