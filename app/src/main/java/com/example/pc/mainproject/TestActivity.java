package com.example.pc.mainproject;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toolbar;

public class TestActivity extends AppCompatActivity {

    public int toPixels(float input){
        Resources r = getResources();
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,input, r.getDisplayMetrics());
    }

    Toolbar toolbar;
    LinearLayout dateBlock, timeBlock, categoryBlock, valueBlock;
    EditText inputSum, inputComment;
    TextView dateText, timeText, categoryText, valueText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int matchParent = LinearLayout.LayoutParams.MATCH_PARENT;
        int wrapContent = LinearLayout.LayoutParams.WRAP_CONTENT;

        //*********ЭЛЕМЕНТЫ ИНТЕРФЕЙСА
        LinearLayout mainLi = new LinearLayout(this);
        mainLi.setOrientation(LinearLayout.VERTICAL);
        mainLi.setBackgroundColor(getResources().getColor(R.color.mainLayout_background));
        LinearLayout.LayoutParams mainLiParams = new LinearLayout.LayoutParams(matchParent,matchParent);

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

        inputSum = new EditText(this);
        inputSum.setLayoutParams(new LinearLayout.LayoutParams(matchParent,wrapContent, 1f));
        inputSum.setInputType(InputType.TYPE_CLASS_NUMBER);
        inputSum.setGravity(Gravity.END);
        inputSum.setHint(R.string.value_hint_sum);
        blockInBlock_4.addView(inputSum);

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

        valueText = new TextView(this);
        valueText.setTextSize(18);
        valueText.setTypeface(Typeface.DEFAULT_BOLD);
        valueText.setPadding(0, toPixels(5),0,0);
        valueText.setText(R.string.value_rub);
        valueText.setLayoutParams(new LinearLayout.LayoutParams(wrapContent,wrapContent, 1f));

        blockInBlock_5.addView(valueText);

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
        setContentView(mainLi, mainLiParams);
    }
}
