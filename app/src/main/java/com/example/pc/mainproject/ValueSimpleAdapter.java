package com.example.pc.mainproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.mainproject.R;

public class ValueSimpleAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    private final String[] values_full;

    public ValueSimpleAdapter(Context context, String[] values, String[] values_full) {
        super(context, R.layout.value_list_item, values);
        this.context = context;
        this.values = values;
        this.values_full = values_full;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.value_list_item, parent, false);
        TextView text_name = (TextView) rowView.findViewById(R.id.value_name);
        TextView text_full_name = (TextView)rowView.findViewById(R.id.value_full_name);


        text_name.setText(values[position]);
        text_full_name.setText(values_full[position]);
        return rowView;
    }
}