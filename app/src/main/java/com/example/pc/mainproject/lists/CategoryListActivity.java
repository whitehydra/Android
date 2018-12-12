package com.example.pc.mainproject.lists;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.pc.mainproject.DBhelper;
import com.example.pc.mainproject.R;

import java.util.ArrayList;
import java.util.Objects;

public class CategoryListActivity extends AppCompatActivity {

    public DBhelper dBhelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        dBhelper = new DBhelper(this);
        db = dBhelper.getWritableDatabase();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ListView lvMain = (ListView) findViewById(R.id.category_list);


        String noteType = getIntent().getStringExtra("NoteType");

        final ArrayList<Integer> keyC = new ArrayList<>();
        ArrayList<String> nameC = new ArrayList<>();



        Cursor c = db.query(DBhelper.TABLE_CATEGORY,null,null,null,null,null,null);
        if(c.moveToFirst()) {
            int idIndex = c.getColumnIndex(DBhelper.CATEGORY_KEY);
            int nameIndex = c.getColumnIndex(DBhelper.CATEGORY_NAME);
            int typeIndex = c.getColumnIndex(DBhelper.CATEGORY_TYPE);

            if(noteType.equals("Расход")){
                do {
                    if(c.getString(typeIndex).equals("Расход")){
                        keyC.add(c.getInt(idIndex));
                        nameC.add(c.getString(nameIndex));
                    }
                } while (c.moveToNext());
            }
            if(noteType.equals("Доход")){
                do {
                    if(c.getString(typeIndex).equals("Доход")){
                        keyC.add(c.getInt(idIndex));
                        nameC.add(c.getString(nameIndex));
                    }
                } while (c.moveToNext());
            }
        }
        c.close();


        CategorySimpleAdapter adapter = new CategorySimpleAdapter(this, nameC.toArray(new String[nameC.size()]));
        lvMain.setAdapter(adapter);

        Log.d("Category: ", "Категория добавлена");
        final Intent answerIntent = new Intent();
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Category log:", "Click");
                answerIntent.putExtra("RESULT",keyC.get(position));
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
