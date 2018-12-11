package com.example.pc.mainproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.pc.mainproject.lists.ValueListActivity;

import java.util.Objects;

public class ValueActivity extends AppCompatActivity {
    String[] data = {"Российский рубль", "two", "three", "four", "five"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    public void openValueList(View view){
        Intent intent = new Intent(ValueActivity.this, ValueListActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
