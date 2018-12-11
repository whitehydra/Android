package com.example.pc.mainproject.lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pc.mainproject.R;

public class CategorySimpleAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public CategorySimpleAdapter(Context context, String[] values) {
        super(context, R.layout.category_list_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.category_list_item, parent, false);
        TextView text_name = (TextView) rowView.findViewById(R.id.category_name);

        text_name.setText(values[position]);
        return rowView;
    }
}
