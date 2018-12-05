package com.example.pc.mainproject;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class CreateNoteActivity extends AppCompatActivity {
    String[] monthName = {"Янв", "Фев","Мар","Апр","Май","Июн","Июл","Авг","Сен","Окт","Ноя","Дек"};

    String noteType;
    TextView actualCategory;
    int listAnswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnote);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.inflateMenu(R.menu.menu_toolbar);

        Date currentTime = Calendar.getInstance().getTime();

        TextView date = findViewById(R.id.date_text);
        TextView time = findViewById(R.id.time_text);
        actualCategory = findViewById(R.id.actualCategory);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat datef = new SimpleDateFormat("dd MMM yyyy");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat timef = new SimpleDateFormat("HH:mm");

        date.setText(datef.format(currentTime));
        time.setText(timef.format(currentTime));


        noteType = getIntent().getStringExtra("NoteType");

        Log.d("MY LOGS: ", noteType);
        if(noteType.equals("Consumption")){
            getSupportActionBar().setTitle("Расход");
            actualCategory.setText("Разное");

        }
        if(noteType.equals("Income")){
            getSupportActionBar().setTitle("Доход");
            actualCategory.setText("Премия");

        }



        View date_button = findViewById(R.id.date_button);
        View time_button = findViewById(R.id.time_button);

        View.OnClickListener oclkBut = new View.OnClickListener() {
            @Override
            public void onClick(View v) { ;
                switch (v.getId()) {
                    case R.id.date_button:

                        Calendar calendar = Calendar.getInstance();
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(CreateNoteActivity.this,
                                new DatePickerDialog.OnDateSetListener() {
                                    @SuppressLint("SetTextI18n")
                                    @Override
                                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                        TextView date = findViewById(R.id.date_text);
                                        date.setText(day + " " + monthName[month] + " " + year);

                                    }
                                }, year, month, dayOfMonth);
                        datePickerDialog.show();
                        break;
                    case R.id.time_button:
                        calendar = Calendar.getInstance();
                        int hour = calendar.get(Calendar.HOUR);
                        int minute = calendar.get(Calendar.MINUTE);
                        TimePickerDialog timePickerDialog = new TimePickerDialog(CreateNoteActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @SuppressLint("SetTextI18n")
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        TextView time = findViewById(R.id.time_text);
                                        time.setText(hourOfDay + ":" + String.format("%2s",minute).replace(' ', '0'));
                                    }
                                }, hour, minute, true);
                        timePickerDialog.show();
                        break;
                }
            }
        };
        date_button.setOnClickListener(oclkBut);
        time_button.setOnClickListener(oclkBut);

    }



    public void openValueList(View view){
        Intent intent = new Intent(CreateNoteActivity.this, ValueListActivity.class);
        startActivity(intent);
    }

    public void openCategoryList(View view){
        Intent intent = new Intent(CreateNoteActivity.this, CategoryListActivity.class);
        startActivityForResult(intent,listAnswer);
        //startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == listAnswer){
            if(resultCode == RESULT_OK){
                int listNum = data.getIntExtra("RESULT",0);
                String category = (getResources().getStringArray(R.array.category_name_array))[listNum];
                Log.d("MY LOGS:", category);
                actualCategory.setText(category);
            }
        }
    }









    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

