package com.example.letscook.DAO;

import static com.example.letscook.db.MyDB.USER_ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.letscook.db.MyDB;
import com.example.letscook.model.Note;

import java.util.ArrayList;

public class NoteDAO {
    private MyDB dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public NoteDAO(Context c){
        context = c;
    }
    public NoteDAO open() throws SQLException {
        dbHelper = new MyDB(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }

    public ArrayList<Note> getAll(String _idUser) {
        Cursor cursorCourses = database.rawQuery("SELECT * FROM " + MyDB.TBL_NOTE + "\n where " + USER_ID + " = " + _idUser, null);
        ArrayList<Note> courseModalArrayList = new ArrayList<>();
        if (cursorCourses.moveToFirst()) {
            do {
                courseModalArrayList.add(new Note(
                        cursorCourses.getString(0),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4)));
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return courseModalArrayList;
    }

    public void delete(String _id , String _idUser){
        database.delete(MyDB.TBL_NOTE,
                MyDB.NOTE_ID + " = " + _id + " and " + USER_ID + " = " + _idUser ,
                null);
    }

    public void insert(Note note){
        ContentValues contentValues =new ContentValues();
        contentValues.put(MyDB.NOTE_NAME,note.getNoteName());
        contentValues.put(MyDB.NOTE_CONTENT,note.getNoteContent());
        contentValues.put(USER_ID,note.getUserId());
        contentValues.put(MyDB.FIRT_REGISTER_PTTM,note.getFrstRegistPttm());
        database.insert(MyDB.TBL_NOTE, null,contentValues);
    }

    public int update (String _id, String name, String contentNote, String _idUser){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDB.NOTE_NAME, name);
        contentValues.put(MyDB.NOTE_CONTENT, contentNote);
        int i =database.update(MyDB.TBL_NOTE,
                contentValues, MyDB.NOTE_ID
                        + " = " + _id + " and " + USER_ID + " = " + _idUser, null);
        return i;
    }

}
