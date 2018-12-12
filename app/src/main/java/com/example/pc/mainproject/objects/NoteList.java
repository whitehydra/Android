package com.example.pc.mainproject.objects;

import android.database.Cursor;
import android.util.Log;

import com.example.pc.mainproject.DBhelper;

import java.io.Serializable;
import java.util.ArrayList;

public class NoteList implements Serializable {
    private String Tag = "Note list: ";
    private ArrayList<Note> list;
    public NoteList(){
        list = new ArrayList<Note>();
    }
    public void add(Note note){
        list.add(note);
    }
    public ArrayList<Note> getList(){
        return list;
    }

    public Note getElement(int id){
        return list.get(id);
    }

    public Boolean isEmpty(){
        return list.isEmpty();
    }

    public int getSize(){
        return list.size();
    }

    public void update(Cursor c){
        list.clear();
        if(c.moveToFirst()) {
            int[] index = {c.getColumnIndex(DBhelper.KEY_ID),c.getColumnIndex(DBhelper.VALUE),
            c.getColumnIndex(DBhelper.DATE),c.getColumnIndex(DBhelper.COMMENT),c.getColumnIndex(DBhelper.TYPE),
            c.getColumnIndex(DBhelper.CATEGORY), c.getColumnIndex(DBhelper.CURRENCY)};
            do {
                Note note = new Note(c.getInt(index[1]),c.getString(index[2]),c.getString(index[3]),
                        c.getString(index[4]),c.getString(index[5]),c.getString(index[6]));
                list.add(note);

                Log.d(Tag, "Получена запись: " + c.getInt(index[0]) + " " +
                        c.getInt(index[1]) + " " + c.getString(index[2]) + " " +
                        c.getString(index[3]) + " " + c.getString(index[4]) + " " +
                        c.getString(index[5]) + " " + c.getString(index[6]));
            } while (c.moveToNext());
        }
        else Log.d(Tag, "Строк не обнаружено");
        c.close();
    }

}
