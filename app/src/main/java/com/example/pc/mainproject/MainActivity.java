package com.example.pc.mainproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.pc.mainproject.objects.Note;
import com.example.pc.mainproject.objects.NoteList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.SimpleTimeZone;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public DBhelper dBhelper;
    private SQLiteDatabase db;

    String Tag = "Main: ";
    NoteList consumptionNotes = new NoteList();
    NoteList incomeNotes = new NoteList();
    TextView consumptionDay,consumptionWeek,consumptionMonth,incomeMonth,allNotes;
    Cursor cursor;
    int createNoteAnswer = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
       // Log.d(Tag,"OnCreate");

        dBhelper = new DBhelper(this);
        db = dBhelper.getWritableDatabase();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button add_button = (Button)findViewById(R.id.button_add1);
        Button add_button2 = (Button)findViewById(R.id.button_add2);
        Button add_button3 = (Button)findViewById(R.id.button_add3);
        Button buttonAllConsumption = (Button)findViewById(R.id.button_all_consumption);
        Button buttonAllIncome = (Button)findViewById(R.id.button_all_income);

        consumptionDay = (TextView)findViewById(R.id.text_count1);
        consumptionWeek = (TextView)findViewById(R.id.text_count2);
        consumptionMonth = (TextView)findViewById(R.id.text_count3);
        incomeMonth = (TextView)findViewById(R.id.text_count4);
        allNotes = (TextView)findViewById(R.id.text_count5);

        View.OnClickListener oclkBut = new View.OnClickListener() {
            @Override
            public void onClick(View v) { ;
                switch (v.getId()) {
                    case R.id.button_add1:
                        Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                        intent.putExtra("NoteType","Consumption");
                        startActivity(intent);
                        break;
                    case R.id.button_add2:
                        intent = new Intent(MainActivity.this, CreateNoteActivity.class);
                        intent.putExtra("NoteType","Income");
                        startActivity(intent);
                        break;
                    case R.id.button_add3:
                        intent = new Intent(MainActivity.this, TestActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.button_all_consumption:
                        intent = new Intent(MainActivity.this, AllNotes.class);
                        intent.putExtra("AllNotes",consumptionNotes);
                        startActivity(intent);
                        break;
                    case R.id.button_all_income:
                        intent = new Intent(MainActivity.this, AllNotes.class);
                        intent.putExtra("AllNotes",incomeNotes);
                        startActivity(intent);
                        break;
                }
            }
        };
        add_button.setOnClickListener(oclkBut);
        add_button2.setOnClickListener(oclkBut);
        add_button3.setOnClickListener(oclkBut);
        buttonAllConsumption.setOnClickListener(oclkBut);
        buttonAllIncome.setOnClickListener(oclkBut);



      //  db.insert(DBhelper.TABLE_NOTE,null,cv);

        //READ

        cursor = db.query(DBhelper.TABLE_NOTE,null,DBhelper.TYPE + " = ?",
                new String[]{"Consumption"},null,null,null);
        consumptionNotes.update(cursor);

        cursor = db.query(DBhelper.TABLE_NOTE,null,DBhelper.TYPE + " = ?",
                new String[]{"Income"},null,null,null);
        incomeNotes.update(cursor);


        //delete

     //   db.delete(DBhelper.TABLE_NOTE,null,null);
     //   db.close();












//
//        ContentValues cv = new ContentValues();
//        SQLiteDatabase db = dBhelper.getWritableDatabase();
//
//        cv.put("name", "ZDAROVA");
//        cv.put("email", "zdarova.com");
//        long rowID = db.insert("mytable", null,cv);
//        Log.d(Tag, "Строка вставленна, ид - " + rowID);
//
//        Log.d(Tag, "Строки таблицы: ");
//        Cursor c = db.query("mytable",null,null,null,null,null,null);
//        if(c.moveToFirst()) {
//            int idColIndex = c.getColumnIndex("id");
//            int nameColIndex = c.getColumnIndex("name");
//            int emailColIndex = c.getColumnIndex("email");
//
//            do {
//                Log.d(Tag, "ID = " + c.getInt(idColIndex) + ", name = " + c.getString(nameColIndex) +
//                        ", email = " + c.getString(emailColIndex));
//            } while (c.moveToNext());
//        }
//        else Log.d(Tag,"0 rows");
//        c.close();
//
//        Log.d(Tag, "Очистка таблиц");
//        int clearCount = db.delete("mytable", null, null);
//        Log.d(Tag,"Удалено строк: " + clearCount);



    }

    @Override
    protected void onResume(){
        super.onResume();
        cursor = db.query(DBhelper.TABLE_NOTE,null,DBhelper.TYPE + " = ?",
                new String[]{"Consumption"},null,null,null);
        consumptionNotes.update(cursor);

        cursor = db.query(DBhelper.TABLE_NOTE,null,DBhelper.TYPE + " = ?",
                new String[]{"Income"},null,null,null);
        incomeNotes.update(cursor);

        printData();
        Log.d(Tag,"Activity resume");
    }


    @SuppressLint("DefaultLocale")
    private void printData(){
        Calendar currentTime = Calendar.getInstance();
        float consumptionOnDay=0,consumptionOnWeek=0,consumptionOnMonth=0, incomeValue=0, allValue=0;
        if(!consumptionNotes.isEmpty())Log.d(Tag,"Objects founded");

        for(Note element:consumptionNotes.getList()){
            if (currentTime.get(Calendar.YEAR)== element.getTime().get(Calendar.YEAR)){
                if (currentTime.get(Calendar.MONTH)== element.getTime().get(Calendar.MONTH))
                {
                    consumptionOnMonth += element.getValue();
                    if (currentTime.get(Calendar.WEEK_OF_MONTH)== element.getTime().get(Calendar.WEEK_OF_MONTH)) {
                        consumptionOnWeek += element.getValue();
                        if (currentTime.get(Calendar.DAY_OF_WEEK)== element.getTime().get(Calendar.DAY_OF_WEEK))
                            consumptionOnDay += element.getValue();
                    }
                }
            }
            allValue -= element.getValue();

        }

        for (Note element: incomeNotes.getList()){
            if (currentTime.get(Calendar.YEAR) == element.getTime().get(Calendar.YEAR)){
                if (currentTime.get(Calendar.MONTH)== element.getTime().get(Calendar.MONTH)){
                    incomeValue += element.getValue();
                }
                allValue += element.getValue();
            }
        }
        consumptionDay.setText(String.format("%6.2f", consumptionOnDay));
        consumptionWeek.setText(String.format("%6.2f", consumptionOnWeek));
        consumptionMonth.setText(String.format("%6.2f", consumptionOnMonth));
        incomeMonth.setText(String.format("%6.2f", incomeValue));
        allNotes.setText(String.format("%6.2f", allValue));
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_value) {
            Intent intent = new Intent(MainActivity.this, ValueActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_copy) {
            Intent intent = new Intent(MainActivity.this, ReservActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_protect) {
            Intent intent = new Intent(MainActivity.this, ProtectionActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_send) {
            String head = "Head";
            String body = "Body";

            String mailString = "mailto:" + "whitehydra@yandex.ru" + "?subject=" + head +
                    "&body=" + body;

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse(mailString));
            startActivity(emailIntent);

        }else if (id == R.id.nav_info) {
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDestroy(){
        db.close();
        super.onDestroy();
    }

}
