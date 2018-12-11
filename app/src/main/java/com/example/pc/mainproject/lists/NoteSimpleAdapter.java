package com.example.pc.mainproject.lists;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pc.mainproject.R;

public class NoteSimpleAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] noteTime;
    private final String[] noteType;
    private final String[] noteValueType;
    private final String[] noteComment;
    private final int[] noteValue;



    public NoteSimpleAdapter(Context context, String[] noteTime,String[] noteType,String[] noteValueType,
                             String[] noteComment, int[] noteValue) {
        super(context, R.layout.note_list_item, noteTime);
        this.context = context;
        this.noteTime = noteTime;
        this.noteType = noteType;
        this.noteValueType = noteValueType;
        this.noteComment = noteComment;
        this.noteValue = noteValue;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.note_list_item, parent, false);
        TextView note_date = (TextView) rowView.findViewById(R.id.note_date);
        TextView note_value = (TextView) rowView.findViewById(R.id.note_value);
        TextView note_type = (TextView) rowView.findViewById(R.id.note_type);
        TextView note_value_type = (TextView) rowView.findViewById(R.id.note_value_type);
        TextView note_comment = (TextView) rowView.findViewById(R.id.note_comment);

        note_date.setText(noteTime[position]);
        note_value.setText(String.format("%6.2f",(float)noteValue[position]));
        note_type.setText(noteType[position]);
        note_value_type.setText(noteValueType[position]);
        note_comment.setText(noteComment[position]);

        return rowView;
    }
}