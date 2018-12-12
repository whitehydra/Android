package com.example.pc.mainproject;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBhelper extends SQLiteOpenHelper {
    private static String TAG = "DATA_BASE: ";
    private static String DB_NAME = "main.db";
    private static String DB_PATH = "";
    private static int DB_VERSION = 1;

    private Context context;
    private boolean update = false;
    private SQLiteDatabase mDataBase;


    public static final String TABLE_NOTE = "NOTE";
    public static final String KEY_ID = "ID";
    public static final String VALUE = "VALUE";
    public static final String DATE = "DATE";
    public static final String COMMENT = "COMMENT";
    public static final String TYPE = "TYPE";
    public static final String CATEGORY = "CATEGORY";
    public static final String CURRENCY = "CURRENCY";




    public DBhelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table "+ TABLE_NOTE +"("+ KEY_ID +" integer primary key autoincrement, "+
                VALUE +" integer, "+ DATE +" text," + COMMENT + " text," + TYPE + " text," + CATEGORY +
        " text," + CURRENCY + " text)");
        Log.d(TAG,"База созданна");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + TABLE_NOTE);
        onCreate(db);

        Log.d(TAG, "База обновлена");
       // if(newVersion > oldVersion)update = true;
    }
}
