package com.example.pc.mainproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.pc.mainproject.lists.ValueListActivity;

import java.util.Objects;

public class ValueActivity extends AppCompatActivity {
    String[] data = {"Российский рубль", "two", "three", "four", "five"};
    int valueAnswer = 1;
    public DBhelper dBhelper;
    private SQLiteDatabase db;

    TextView textMain, textName, textFullName;

    protected void showData(){
        SharedPreferences sp = this.getSharedPreferences("com.example.pc.mainproject", MODE_PRIVATE);
        String defaultName = sp.getString("Curse_name","RUB");
        String defaultFullName = sp.getString("Curse_full_name", "Российский рубль");

        textMain.setText(defaultFullName);
        textName.setText(defaultName);
        textFullName.setText(defaultFullName);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value);

        dBhelper = new DBhelper(this);
        db = dBhelper.getWritableDatabase();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textMain = (TextView) findViewById(R.id.textMainValue);
        textName = (TextView) findViewById(R.id.textName);
        textFullName = (TextView) findViewById(R.id.textFullName);

        showData();
    }

    public void openValueList(View view){
        Intent intent = new Intent(ValueActivity.this, ValueListActivity.class);
        startActivityForResult(intent, valueAnswer);
    }


    @Override
    protected void onResume(){
        super.onResume();
        showData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == valueAnswer){
            if(resultCode == RESULT_OK){
                int listNum = data.getIntExtra("RESULT",0);

                String name = Query.getValueNameById(db,listNum);
                String full_name = Query.getValueFullNameById(db,listNum);
                Float curse = Query.getCurseByName(db, name);

                Log.d("VALUE ACTIVITY ", "new course = " + curse);
                SharedPreferences sp = this.getSharedPreferences("com.example.pc.mainproject", MODE_PRIVATE);

                sp.edit().putFloat("Curse",curse).apply();
                sp.edit().putString("Curse_name",name).apply();
                sp.edit().putString("Curse_full_name",full_name).apply();
            }
        }
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
