package com.example.letscook.DAO;

import static com.example.letscook.db.MyDB.RECIPE_ID;
import static com.example.letscook.db.MyDB.RECIPE_NAME;
import static com.example.letscook.db.MyDB.TBL_RECIPE;
import static com.example.letscook.db.MyDB.TBL_THEME;
import static com.example.letscook.db.MyDB.TBL_THEME_RECIPE;
import static com.example.letscook.db.MyDB.THEME_ID;
import static com.example.letscook.db.MyDB.THEME_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.letscook.db.MyDB;
import com.example.letscook.model.Recipe;

import java.util.ArrayList;

public class RecipeDAO {
    private MyDB dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public RecipeDAO(Context c){
        context = c;
    }
    public RecipeDAO open() throws SQLException {
        dbHelper = new MyDB(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }

    public ArrayList<Recipe> getAllRecipe() {
        Cursor cursorCourses = database.rawQuery("SELECT rc." + RECIPE_NAME + ", ct." + THEME_NAME +
                " FROM "+ TBL_RECIPE + " rc " +
                " INNER JOIN " + TBL_THEME_RECIPE + " trc ON rc." + RECIPE_ID + " = trc." +RECIPE_ID +
                " INNER JOIN " + TBL_THEME + " th ON th." + THEME_ID + " = trc." + THEME_ID, null);
        ArrayList<Recipe> courseModalArrayList = new ArrayList<>();
        if (cursorCourses.moveToFirst()) {
            do {
                courseModalArrayList.add(new Recipe(
                        cursorCourses.getString(0),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3)));
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return courseModalArrayList;
    }
/*
    public void delete(String _id){
        database.delete(MyDB.TBL_RECIPE,
                MyDB.RECIPE_ID + " = " + _id,
                null);
    }*/

    public void insert(Recipe recipe){
        ContentValues contentValues =new ContentValues();
        contentValues.put(MyDB.RECIPE_NAME,recipe.getRecipeName());
        contentValues.put(MyDB.RECIPE_DETAIL,recipe.getRecipeDetail());
        contentValues.put(MyDB.RECIPE_AVATAR,recipe.getRecipeAvatar());
        database.insert(MyDB.TBL_RECIPE, null,contentValues);
    }
}
