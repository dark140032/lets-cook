package com.example.letscook.DAO;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.letscook.db.MyDB;

public class NoteDAO {
    private MyDB dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public NoteDAO(Context c){
        context = c;
    }
    public  NoteDAO open() throws SQLException {
        dbHelper = new MyDB(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }
}
