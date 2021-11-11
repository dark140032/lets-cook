package com.example.letscook.DAO;

import static com.example.letscook.db.MyDB.RECIPE_AVATAR;
import static com.example.letscook.db.MyDB.RECIPE_DES;
import static com.example.letscook.db.MyDB.RECIPE_ID;
import static com.example.letscook.db.MyDB.RECIPE_MAKING;
import static com.example.letscook.db.MyDB.RECIPE_MATERIAL;
import static com.example.letscook.db.MyDB.RECIPE_NAME;
import static com.example.letscook.db.MyDB.TBL_RECIPE;
import static com.example.letscook.db.MyDB.TBL_USER;
import static com.example.letscook.db.MyDB.TBL_WISHLIST;
import static com.example.letscook.db.MyDB.USER_ID;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.letscook.db.MyDB;
import com.example.letscook.model.Recipe;

import java.util.ArrayList;

public class SearchDAO {
    private MyDB dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public SearchDAO(Context c){
        context = c;
    }
    public SearchDAO open() throws SQLException {
        dbHelper = new MyDB(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }

    public ArrayList<Recipe> getAllRecipe() {
        Cursor cursorCourses = database.rawQuery("SELECT * " +
                " From "+ TBL_RECIPE , null);

        ArrayList<Recipe> courseModalArrayList = new ArrayList<>();
        if (cursorCourses.moveToFirst()) {
            do {
                courseModalArrayList.add(new Recipe(
                        cursorCourses.getString(0),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),
                        cursorCourses.getString(5)));
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return courseModalArrayList;
    }

}
