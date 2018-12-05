package com.example.pc.mainproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Objects;

public class CategoryListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ListView lvMain = (ListView) findViewById(R.id.category_list);


        String noteType = getIntent().getStringExtra("NoteType");
        String[] category_name = null;


        if(noteType.equals("Consumption")){
            category_name = getResources().getStringArray(R.array.category_consumption_array);
        }
        if(noteType.equals("Income")){
            category_name = getResources().getStringArray(R.array.category_income_array);
        }


        CategorySimpleAdapter adapter = new CategorySimpleAdapter(this, category_name);
        lvMain.setAdapter(adapter);

        Log.d("Category log:", "Created");
        final Intent answerIntent = new Intent();
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Category log:", "Click");
                answerIntent.putExtra("RESULT",position);
                setResult(RESULT_OK,answerIntent);
                finish();
            }
        });
        lvMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Category log:", "SELECT");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
