package com.example.pc.mainproject.lists;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pc.mainproject.DBhelper;
import com.example.pc.mainproject.R;

import java.util.ArrayList;
import java.util.Objects;

public class ValueListActivity extends AppCompatActivity {

    public DBhelper dBhelper;
    private SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_list);

        dBhelper = new DBhelper(this);
        db = dBhelper.getWritableDatabase();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        ListView lvMain = (ListView) findViewById(R.id.value_list);



        final ArrayList<Integer> keyL = new ArrayList<>();
        ArrayList<String> nameL = new ArrayList<>();
        ArrayList<String> fullNameL = new ArrayList<>();



        Cursor c = db.query(DBhelper.TABLE_VALUE,null,null,null,null,null,null);
        if(c.moveToFirst()) {
            int idIndex = c.getColumnIndex(DBhelper.VALUE_KEY);
            int nameIndex = c.getColumnIndex(DBhelper.VALUE_NAME);
            int fullNameIndex = c.getColumnIndex(DBhelper.VALUE_FULL_NAME);

            do {
                keyL.add(c.getInt(idIndex));
                nameL.add(c.getString(nameIndex));
                fullNameL.add(c.getString(fullNameIndex));
            } while (c.moveToNext());
        }
        c.close();



        ValueSimpleAdapter adapter = new ValueSimpleAdapter(this, nameL.toArray(new String[nameL.size()]),
                fullNameL.toArray(new String[fullNameL.size()]));
        lvMain.setAdapter(adapter);

        Log.d("Value log:", "Created");
        final Intent answerIntent = new Intent();
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Value log:", "Click");
                answerIntent.putExtra("RESULT",keyL.get(position));
                setResult(RESULT_OK,answerIntent);
                finish();
            }
        });
        lvMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Value log:", "SELECT");
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


    @Override
    public void onDestroy(){
        db.close();
        super.onDestroy();
    }

}
