package com.example.pc.mainproject;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Objects;

public class ReservActivity extends AppCompatActivity {


    public DBhelper dBhelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserv);

        dBhelper = new DBhelper(this);
        db = dBhelper.getWritableDatabase();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void cleanStory(View v){
        db.delete(DBhelper.TABLE_NOTE,null,null);
        dBhelper.upgradeNote(db);
    }

    @Override
    public void onDestroy(){
        db.close();
        super.onDestroy();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
