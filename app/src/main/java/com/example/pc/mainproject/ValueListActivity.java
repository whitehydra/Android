package com.example.pc.mainproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.Objects;

public class ValueListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        ListView lvMain = (ListView) findViewById(R.id.value_list);

        String[] value_name = getResources().getStringArray(R.array.value_name_array);
        String[] value_full_name = getResources().getStringArray(R.array.value_full_name_array);


        ValueSimpleAdapter adapter = new ValueSimpleAdapter(this, value_name, value_full_name);
        lvMain.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
