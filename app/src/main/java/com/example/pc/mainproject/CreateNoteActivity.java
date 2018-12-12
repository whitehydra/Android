package com.example.pc.mainproject;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.pc.mainproject.lists.CategoryListActivity;
import com.example.pc.mainproject.lists.ValueListActivity;
import com.example.pc.mainproject.objects.Note;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class CreateNoteActivity extends AppCompatActivity {
    String[] monthName = {"Янв", "Фев","Мар","Апр","Май","Июн","Июл","Авг","Сен","Окт","Ноя","Дек"};

    String noteType;
    int categoryAnswer = 0, valueAnswer = 1;


    public DBhelper dBhelper;
    private SQLiteDatabase db;

    Toolbar toolbar;
    LinearLayout dateBlock, timeBlock, categoryBlock, valueBlock;
    EditText inputValue, inputComment;
    TextView dateText, timeText, categoryText, currencyText;
    String Tag = "CreateNote: ";

    Calendar selectedTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View content = drawContent();
        setContentView(content);

        dBhelper = new DBhelper(this);
        db = dBhelper.getWritableDatabase();

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.inflateMenu(R.menu.menu_toolbar);

        Date currentTime = Calendar.getInstance().getTime();

        @SuppressLint("SimpleDateFormat") final SimpleDateFormat datef = new SimpleDateFormat("dd MMM yyyy");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat timef = new SimpleDateFormat("HH:mm");

        selectedTime.setTime(currentTime);
        dateText.setText(datef.format(currentTime));
        timeText.setText(timef.format(currentTime));

        noteType = getIntent().getStringExtra("NoteType");

        Log.d(Tag, "noteType - " + noteType);
        if(noteType.equals("Расход")){
            getSupportActionBar().setTitle("Расход");
            categoryText.setText("Разное");

        }
        if(noteType.equals("Доход")){
            getSupportActionBar().setTitle("Доход");
            categoryText.setText("Премия");
        }

        View.OnClickListener oclkBut = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == dateBlock){
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(CreateNoteActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @SuppressLint("SetTextI18n")
                                @Override
                                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                    dateText.setText(day + " " + monthName[month] + " " + year);
                                    selectedTime.set(Calendar.YEAR, year);
                                    selectedTime.set(Calendar.MONTH, month);
                                    selectedTime.set(Calendar.DAY_OF_MONTH, day);
                                }
                            }, year, month, dayOfMonth);
                    datePickerDialog.show();
                }

                if (v == timeBlock){
                    Calendar calendar = Calendar.getInstance();
                    final int hour = calendar.get(Calendar.HOUR);
                    int minute = calendar.get(Calendar.MINUTE);
                    TimePickerDialog timePickerDialog = new TimePickerDialog(CreateNoteActivity.this,
                            new TimePickerDialog.OnTimeSetListener() {
                                @SuppressLint("SetTextI18n")
                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                    timeText.setText(hourOfDay + ":" + String.format("%2s",minute).
                                            replace(' ', '0'));
                                    selectedTime.set(Calendar.HOUR, hourOfDay);
                                    selectedTime.set(Calendar.MINUTE, minute);
                                }
                            }, hour, minute, true);
                    timePickerDialog.show();
                }
                if (v == categoryBlock){
                    Intent intent = new Intent(CreateNoteActivity.this, CategoryListActivity.class);
                    intent.putExtra("NoteType",noteType);
                    startActivityForResult(intent, categoryAnswer);
                }
                if (v == valueBlock){
                    Intent intent = new Intent(CreateNoteActivity.this, ValueListActivity.class);
                    startActivityForResult(intent, valueAnswer);
                }
            }
        };
        dateBlock.setOnClickListener(oclkBut);
        timeBlock.setOnClickListener(oclkBut);
        categoryBlock.setOnClickListener(oclkBut);
        valueBlock.setOnClickListener(oclkBut);

        Toolbar.OnMenuItemClickListener toolbarListener = new Toolbar.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case (R.id.save):
                        Log.d(Tag, "Add note");
                        int value;
                        if(inputValue.getText().toString().equals(""))value=0;
                        else value = Integer.parseInt(inputValue.getText().toString());

                        Note note = new Note(value, selectedTime,inputComment.getText().toString(), noteType,
                                categoryText.getText().toString(), currencyText.getText().toString());
                        note.convertValue(Query.getCurseByName(db,note.getCurrency()));
                        Log.d(Tag, note.getFullInfo());

                        int valueKey = Query.getValueIdByName(db, note.getCurrency());
                        int categoryKey = Query.getCategoryIdByName(db, note.getCategory());
                        long rowID = db.insert(DBhelper.TABLE_NOTE,null,note.getContentValues(valueKey, categoryKey));
                        Log.d(Tag, "ЕСТЬ, ид - " + rowID);
                        finish();
                }
                return false;
            }
        };
        toolbar.setOnMenuItemClickListener(toolbarListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == categoryAnswer){
            if(resultCode == RESULT_OK){
                int listNum = data.getIntExtra("RESULT",0);
                String category = Query.getCategoryNameById(db,listNum);

                Log.d(Tag, "returned - " + category);
                categoryText.setText(category);
            }
        }
        if (requestCode == valueAnswer){
            if(resultCode == RESULT_OK){
                int listNum = data.getIntExtra("RESULT",0);

                String value = Query.getValueNameById(db,listNum);
                Log.d(Tag, "returned - " + value + " id = " + Query.getValueIdByName(db, value));
                currencyText.setText(value);
            }
        }
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




    private LinearLayout drawContent(){
        int matchParent = LinearLayout.LayoutParams.MATCH_PARENT;
        int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

        //*********ЭЛЕМЕНТЫ ИНТЕРФЕЙСА
        LinearLayout mainLi = new LinearLayout(this);
        mainLi.setOrientation(LinearLayout.VERTICAL);
        mainLi.setBackgroundColor(getResources().getColor(R.color.mainLayout_background));

        //**Тулбар
        toolbar = new Toolbar(this);
        toolbar.setBackgroundColor(getResources().getColor(R.color.title_color));
        mainLi.addView(toolbar, new LinearLayout.LayoutParams(matchParent, toPixels(56)));

        LinearLayout mainBlock = new LinearLayout(this);
        mainBlock.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams mainBlockParams = new LinearLayout.LayoutParams(matchParent,wrapContent);
        mainBlockParams.leftMargin = toPixels(10);
        mainBlockParams.rightMargin = toPixels(10);
        mainBlockParams.topMargin = toPixels(20);
        mainBlock.setElevation(toPixels(3));
        mainBlock.setBackground(getResources().getDrawable(R.drawable.layout_blocks));

        LinearLayout noteBlock_1 = new LinearLayout(this);
        noteBlock_1.setOrientation(LinearLayout.HORIZONTAL);

        //**Блок первый (Дата)
        dateBlock = new LinearLayout(this);
        dateBlock.setOrientation(LinearLayout.VERTICAL);
        dateBlock.setPadding(toPixels(10),toPixels(10),toPixels(10),toPixels(10));

        TextView textDay = new TextView(this);
        textDay.setTextSize(12);
        textDay.setTextColor(getResources().getColor(R.color.grey_light));
        textDay.setText(R.string.day);
        dateBlock.addView(textDay, matchParent, wrapContent);

        LinearLayout blockInBlock_1 = new LinearLayout(this);
        blockInBlock_1.setOrientation(LinearLayout.HORIZONTAL);

        dateText = new TextView(this);
        dateText.setTextSize(18);
        dateText.setTypeface(Typeface.DEFAULT_BOLD);
        dateText.setPadding(0, toPixels(5),0,0);
        dateText.setTextColor(getResources().getColor(R.color.blue));
        dateText.setText(R.string.default_date);
        blockInBlock_1.addView(dateText);

        ImageView imageOpen_1 = new ImageView(this);
        imageOpen_1.setBackgroundResource(R.drawable.ic_open);
        blockInBlock_1.addView(imageOpen_1, matchParent, matchParent);

        dateBlock.addView(blockInBlock_1, matchParent, matchParent);

        noteBlock_1.addView(dateBlock, wrapContent, matchParent);

        //**Блок второй (Время)
        timeBlock = new LinearLayout(this);
        timeBlock.setOrientation(LinearLayout.VERTICAL);
        timeBlock.setPadding(toPixels(10),toPixels(10),toPixels(10),toPixels(10));

        TextView textTime = new TextView(this);
        textTime.setTextSize(12);
        textTime.setTextColor(getResources().getColor(R.color.grey_light));
        textTime.setText(R.string.time);

        timeBlock.addView(textTime, matchParent, wrapContent);

        LinearLayout blockInBlock_2 = new LinearLayout(this);
        blockInBlock_2.setOrientation(LinearLayout.HORIZONTAL);

        timeText = new TextView(this);
        timeText.setTextSize(18);
        timeText.setTypeface(Typeface.DEFAULT_BOLD);
        timeText.setPadding(0, toPixels(5),0,0);
        timeText.setTextColor(getResources().getColor(R.color.blue));
        timeText.setText(R.string.default_time);
        blockInBlock_2.addView(timeText);

        Space space2 = new Space(this);
        blockInBlock_2.addView(space2, toPixels(35), wrapContent);

        ImageView imageOpen_2 = new ImageView(this);
        imageOpen_2.setBackgroundResource(R.drawable.ic_open);

        blockInBlock_2.addView(imageOpen_2, wrapContent, matchParent);
        timeBlock.addView(blockInBlock_2,matchParent,matchParent);
        noteBlock_1.addView(timeBlock,wrapContent,matchParent);
        mainBlock.addView(noteBlock_1,matchParent,matchParent);

        View line1 = new View(this);
        line1.setBackgroundColor(getResources().getColor(R.color.black_light));
        mainBlock.addView(line1,matchParent,toPixels(1));

        //**Блок третий (Категория)
        categoryBlock = new LinearLayout(this);
        categoryBlock.setOrientation(LinearLayout.VERTICAL);
        categoryBlock.setPadding(toPixels(10),toPixels(10),toPixels(10),toPixels(10));

        TextView textCategory = new TextView(this);
        textCategory.setTextSize(12);
        textCategory.setTextColor(getResources().getColor(R.color.grey_light));
        textCategory.setText(R.string.category);
        categoryBlock.addView(textCategory, matchParent, wrapContent);

        LinearLayout blockInBlock_3 = new LinearLayout(this);

        categoryText = new TextView(this);
        categoryText.setTextSize(18);
        categoryText.setTypeface(Typeface.DEFAULT_BOLD);
        categoryText.setPadding(0, toPixels(5),0,0);
        categoryText.setText(R.string.category_different);
        categoryText.setLayoutParams(new LinearLayout.LayoutParams(wrapContent,wrapContent, 1f));

        blockInBlock_3.addView(categoryText);

        ImageView imageOpen_3 = new ImageView(this);
        imageOpen_3.setBackgroundResource(R.drawable.ic_open);

        blockInBlock_3.addView(imageOpen_3, wrapContent, matchParent);
        categoryBlock.addView(blockInBlock_3, matchParent, matchParent);
        mainBlock.addView(categoryBlock,matchParent,matchParent);

        View line2 = new View(this);
        line2.setBackgroundColor(getResources().getColor(R.color.black_light));
        mainBlock.addView(line2,matchParent,toPixels(1));

        //**Блок четвёртый (Сумма)
        LinearLayout noteBlock_2 = new LinearLayout(this);
        noteBlock_2.setOrientation(LinearLayout.VERTICAL);
        noteBlock_2.setPadding(toPixels(10),toPixels(10),toPixels(10),toPixels(10));

        TextView textSum = new TextView(this);
        textSum.setTextSize(12);
        textSum.setTextColor(getResources().getColor(R.color.grey_light));
        textSum.setText(R.string.sum);
        noteBlock_2.addView(textSum, matchParent, wrapContent);

        LinearLayout blockInBlock_4 = new LinearLayout(this);

        ImageView imageOther = new ImageView(this);
        imageOther.setBackgroundResource(R.drawable.ic_sum);
        imageOther.setLayoutParams(new LinearLayout.LayoutParams(toPixels(45),toPixels(45), 0f));
        blockInBlock_4.addView(imageOther);

        inputValue = new EditText(this);
        inputValue.setLayoutParams(new LinearLayout.LayoutParams(matchParent,wrapContent, 1f));
        inputValue.setInputType(InputType.TYPE_CLASS_NUMBER);
        inputValue.setGravity(Gravity.END);
        inputValue.setHint(R.string.value_hint_sum);
        blockInBlock_4.addView(inputValue);

        noteBlock_2.addView(blockInBlock_4, matchParent, matchParent);
        mainBlock.addView(noteBlock_2,matchParent,matchParent);

        View line3 = new View(this);
        line3.setBackgroundColor(getResources().getColor(R.color.black_light));
        mainBlock.addView(line3,matchParent,toPixels(1));

        //**Блок пятый (Валюта)
        valueBlock = new LinearLayout(this);
        valueBlock.setOrientation(LinearLayout.VERTICAL);
        valueBlock.setPadding(toPixels(10),toPixels(10),toPixels(10),toPixels(10));


        TextView textValue = new TextView(this);
        textValue.setTextSize(12);
        textValue.setTextColor(getResources().getColor(R.color.grey_light));
        textValue.setText(R.string.value);
        valueBlock.addView(textValue, matchParent, wrapContent);


        LinearLayout blockInBlock_5 = new LinearLayout(this);

        currencyText = new TextView(this);
        currencyText.setTextSize(18);
        currencyText.setTypeface(Typeface.DEFAULT_BOLD);
        currencyText.setPadding(0, toPixels(5),0,0);
        currencyText.setText(R.string.value_rub);
        currencyText.setLayoutParams(new LinearLayout.LayoutParams(wrapContent,wrapContent, 1f));

        blockInBlock_5.addView(currencyText);

        ImageView imageOpen_4 = new ImageView(this);
        imageOpen_4.setBackgroundResource(R.drawable.ic_open);

        blockInBlock_5.addView(imageOpen_4, wrapContent, matchParent);
        valueBlock.addView(blockInBlock_5, matchParent, matchParent);
        mainBlock.addView(valueBlock,matchParent,matchParent);

        View line4 = new View(this);
        line4.setBackgroundColor(getResources().getColor(R.color.black_light));
        mainBlock.addView(line4,matchParent,toPixels(1));

        //**Блок пятый (Комментарий)
        LinearLayout noteBlock_3 = new LinearLayout(this);
        noteBlock_3.setOrientation(LinearLayout.VERTICAL);
        noteBlock_3.setPadding(toPixels(10),toPixels(10),toPixels(10),toPixels(10));


        inputComment = new EditText(this);
        inputComment.setLayoutParams(new LinearLayout.LayoutParams(matchParent,wrapContent, 1f));
        inputComment.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        inputComment.setGravity(Gravity.END);
        inputComment.setHint(R.string.value_hint_comment);
        noteBlock_3.addView(inputComment);

        mainBlock.addView(noteBlock_3,matchParent,matchParent);
        mainLi.addView(mainBlock,mainBlockParams);
        return mainLi;
    }


    public int toPixels(float input){
        Resources r = getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,input, r.getDisplayMetrics());
    }
}

