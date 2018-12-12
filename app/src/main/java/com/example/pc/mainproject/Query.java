package com.example.pc.mainproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Query {
    static public String getValueNameById(SQLiteDatabase db, int id){
        String output = "";
        Cursor cs = db.query(DBhelper.TABLE_VALUE, null,
                DBhelper.VALUE_KEY + " = ?",
                new String[]{Integer.toString(id)},null,null,null);

        if(cs.moveToFirst()) output = cs.getString(cs.getColumnIndex(DBhelper.VALUE_NAME));
        cs.close();
        return output;
    }

    static public String getValueFullNameById(SQLiteDatabase db, int id){
        String output = "";
        Cursor cs = db.query(DBhelper.TABLE_VALUE, null,
                DBhelper.VALUE_KEY + " = ?",
                new String[]{Integer.toString(id)},null,null,null);

        if(cs.moveToFirst()) output = cs.getString(cs.getColumnIndex(DBhelper.VALUE_FULL_NAME));
        cs.close();
        return output;
    }



    static public int getValueIdByName(SQLiteDatabase db, String name){
        int output = 0;
        Cursor cs = db.query(DBhelper.TABLE_VALUE, null,
                DBhelper.VALUE_NAME + " = ?",
                new String[]{name},null,null,null);

        if(cs.moveToFirst()) output = cs.getInt(cs.getColumnIndex(DBhelper.VALUE_KEY));
        cs.close();
        return output;
    }

    static float getCurseByName(SQLiteDatabase db, String name){
        float output = 0;
        Cursor cs = db.query(DBhelper.TABLE_VALUE, null,
                DBhelper.VALUE_NAME + " = ?",
                new String[]{name},null,null,null);

        if(cs.moveToFirst()) output = cs.getFloat(cs.getColumnIndex(DBhelper.VALUE_COURSE));
        cs.close();
        return output;
    }

}
