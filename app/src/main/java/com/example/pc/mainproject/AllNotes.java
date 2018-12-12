package com.example.pc.mainproject;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.pc.mainproject.lists.NoteSimpleAdapter;
import com.example.pc.mainproject.objects.Note;
import com.example.pc.mainproject.objects.NoteList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllNotes extends AppCompatActivity {

    ArrayList<String> noteDate = new ArrayList<String>();
    ArrayList<String> noteValue = new ArrayList<>();
    ArrayList<String> noteType = new ArrayList<>();
    ArrayList<String> noteComment = new ArrayList<>();
    public DBhelper dBhelper;
    private SQLiteDatabase db;
    float[] value;
    @SuppressLint("SimpleDateFormat") SimpleDateFormat timef = new SimpleDateFormat("dd MMM yyyy HH:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_notes);

        dBhelper = new DBhelper(this);
        db = dBhelper.getWritableDatabase();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ListView lvMain = (ListView) findViewById(R.id.note_list);
        NoteList allNotes  =  (NoteList)getIntent().getSerializableExtra("AllNotes");
        int size = allNotes.getSize();

        value = new float[size];

        if (!allNotes.isEmpty()){
            int i = 0;
            for (Note element: allNotes.getList()){

                //value[i] = element.getValue();

                value[i] = element.getValue() / Query.getCurseByName(db,element.getCurrency());

                noteValue.add(element.getCurrency());
                noteType.add(element.getCategory());
                noteComment.add(element.getComment());
                noteDate.add(timef.format(element.getTime().getTime()));

                i++;
            }


            NoteSimpleAdapter adapter = new NoteSimpleAdapter(this, noteDate.toArray(new String[size]),
                    noteType.toArray(new String[size]),noteValue.toArray(new String[size]),
                    noteComment.toArray(new String[size]),value);
            lvMain.setAdapter(adapter);
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
