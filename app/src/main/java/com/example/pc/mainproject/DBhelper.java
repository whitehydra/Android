package com.example.pc.mainproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBhelper extends SQLiteOpenHelper {
    private static String TAG = "DATA_BASE: ";
    private static String DB_NAME = "main.db";
    private static String DB_PATH = "";
    public static int DB_VERSION = 1;

    private Context context;
    private boolean update = false;
    private SQLiteDatabase mDataBase;


    public static final String TABLE_NOTE = "NOTE";
    public static final String NOTE_ID = "ID";
    public static final String NOTE_VALUE = "VALUE";
    public static final String NOTE_DATE = "DATE";
    public static final String NOTE_COMMENT = "COMMENT";
    public static final String NOTE_TYPE = "TYPE";
    public static final String NOTE_CATEGORY = "CATEGORY";
    public static final String NOTE_CURRENCY = "CURRENCY";

    public static final String TABLE_VALUE = "TABLE_VALUE";
    public static final String VALUE_KEY = "ID";
    public static final String VALUE_NAME = "NAME";
    public static final String VALUE_FULL_NAME = "FULL_NAME";
    public static final String VALUE_COURSE = "COURSE";

    public static final String TABLE_CATEGORY = "TABLE_CATEGORY";
    public static final String CATEGORY_KEY = "CATEGORY_ID";
    public static final String CATEGORY_NAME = "CATEGORY_NAME";
    public static final String CATEGORY_TYPE = "CATEGORY_TYPE";




    public DBhelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table "+ TABLE_NOTE +"("+ NOTE_ID +" integer primary key autoincrement, "+
                NOTE_VALUE +" integer, "+ NOTE_DATE +" text," + NOTE_COMMENT + " text," + NOTE_TYPE + " text," +
                NOTE_CATEGORY +" integer," + NOTE_CURRENCY + " integer)");

        db.execSQL("create table " + TABLE_VALUE + "(" + VALUE_KEY + " integer primary key autoincrement, " +
        VALUE_NAME + " text," + VALUE_FULL_NAME + " text," + VALUE_COURSE + " real)");

        db.execSQL("create table " + TABLE_CATEGORY + "(" + CATEGORY_KEY + " integer primary key autoincrement, "+
        CATEGORY_NAME + " text," + CATEGORY_TYPE + " text)");

        addDefaultFields(db);
        Log.d(TAG,"База созданна");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + TABLE_NOTE);
        db.execSQL("drop table if exists " + TABLE_VALUE);
        db.execSQL("drop table if exists " + TABLE_CATEGORY);
        onCreate(db);

        DB_VERSION = newVersion;
        Log.d(TAG, "База обновлена");
    }


    public void upgradeNote(SQLiteDatabase db){
        db.execSQL("drop table if exists " + TABLE_NOTE);

        db.execSQL("create table "+ TABLE_NOTE +"("+ NOTE_ID +" integer primary key autoincrement, "+
                NOTE_VALUE +" integer, "+ NOTE_DATE +" text," + NOTE_COMMENT + " text," + NOTE_TYPE + " text," + NOTE_CATEGORY +
                " text," + NOTE_CURRENCY + " integer)");
    }



    public void addDefaultFields(SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.VALUE_NAME, "RUB");
        cv.put(DBhelper.VALUE_FULL_NAME, "Российский рубль");
        cv.put(DBhelper.VALUE_COURSE, 1f);
        db.insert(DBhelper.TABLE_VALUE, null,cv);

        cv.put(DBhelper.VALUE_NAME, "USD");
        cv.put(DBhelper.VALUE_FULL_NAME, "Доллар США");
        cv.put(DBhelper.VALUE_COURSE, 66.4f);
        db.insert(DBhelper.TABLE_VALUE, null,cv);

        //////////////

        ContentValues cv2 = new ContentValues();

        cv2.put(DBhelper.CATEGORY_NAME, "Разное");
        cv2.put(DBhelper.CATEGORY_TYPE, "Расход");
        db.insert(DBhelper.TABLE_CATEGORY, null,cv2);

        cv2.put(DBhelper.CATEGORY_NAME, "Услуги");
        cv2.put(DBhelper.CATEGORY_TYPE, "Расход");
        db.insert(DBhelper.TABLE_CATEGORY, null,cv2);

        cv2.put(DBhelper.CATEGORY_NAME, "Покупки");
        cv2.put(DBhelper.CATEGORY_TYPE, "Расход");
        db.insert(DBhelper.TABLE_CATEGORY, null,cv2);

        cv2.put(DBhelper.CATEGORY_NAME, "Премия");
        cv2.put(DBhelper.CATEGORY_TYPE, "Доход");
        db.insert(DBhelper.TABLE_CATEGORY, null,cv2);

        cv2.put(DBhelper.CATEGORY_NAME, "Подарок");
        cv2.put(DBhelper.CATEGORY_TYPE, "Доход");
        db.insert(DBhelper.TABLE_CATEGORY, null,cv2);

        cv2.put(DBhelper.CATEGORY_NAME, "Зарплата");
        cv2.put(DBhelper.CATEGORY_TYPE, "Доход");
        db.insert(DBhelper.TABLE_CATEGORY, null,cv2);
    }

}
