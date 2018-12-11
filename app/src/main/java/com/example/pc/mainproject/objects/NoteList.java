package com.example.pc.mainproject.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class NoteList implements Serializable {
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
}
