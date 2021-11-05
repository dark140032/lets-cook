package com.example.letscook.DAO;

import static com.example.letscook.db.MyDB.CATEGORY_ID;
import static com.example.letscook.db.MyDB.RECIPE_AVATAR;
import static com.example.letscook.db.MyDB.RECIPE_DES;
import static com.example.letscook.db.MyDB.RECIPE_ID;
import static com.example.letscook.db.MyDB.RECIPE_MAKING;
import static com.example.letscook.db.MyDB.RECIPE_MATERIAL;
import static com.example.letscook.db.MyDB.RECIPE_NAME;
import static com.example.letscook.db.MyDB.TBL_CATEGORY;
import static com.example.letscook.db.MyDB.TBL_CATEGORY_RECIPE;
import static com.example.letscook.db.MyDB.TBL_RECIPE;
import static com.example.letscook.db.MyDB.TBL_THEME;
import static com.example.letscook.db.MyDB.TBL_THEME_RECIPE;
import static com.example.letscook.db.MyDB.THEME_ID;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.letscook.db.MyDB;
import com.example.letscook.model.Category;
import com.example.letscook.model.Recipe;
import com.example.letscook.model.Theme;

import java.util.ArrayList;

public class HomeDAO {
    private MyDB dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public HomeDAO(Context c){
        context = c;
    }
    public HomeDAO open() throws SQLException {
        dbHelper = new MyDB(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }


    public ArrayList<Theme> getAllTheme() {
        Cursor cursorCourses = database.rawQuery("SELECT *  FROM "+ TBL_THEME, null);
        ArrayList<Theme> courseModalArrayList = new ArrayList<>();
        if (cursorCourses.moveToFirst()) {
            do {
                courseModalArrayList.add(new Theme(
                        cursorCourses.getString(0),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2)));
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return courseModalArrayList;
    }

    public ArrayList<Recipe> getAllRecipeByThemeId(String id) {
        Cursor cursorCourses = database.rawQuery("SELECT rc."
                                + RECIPE_ID + ", rc."
                                + RECIPE_NAME + ", rc."
                                + RECIPE_DES+ ", rc."
                                + RECIPE_MATERIAL+ ", rc."
                                + RECIPE_MAKING + ", rc."
                                + RECIPE_AVATAR + " FROM "+ TBL_RECIPE + " rc" +
                    " INNER JOIN " + TBL_THEME_RECIPE + " ttr ON ttr." + RECIPE_ID + " = rc." + RECIPE_ID +
                    " INNER JOIN " + TBL_THEME + " th ON th." + THEME_ID + " = " + "ttr." + THEME_ID +
                    " WHERE th." + THEME_ID + " = " + id, null);
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

    public ArrayList<Category> getAllCategory() {
        Cursor cursorCourses = database.rawQuery("SELECT *  FROM "+ TBL_CATEGORY, null);
        ArrayList<Category> courseModalArrayList = new ArrayList<>();
        if (cursorCourses.moveToFirst()) {
            do {
                courseModalArrayList.add(new Category(
                        cursorCourses.getString(0),
                        cursorCourses.getString(1)));
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return courseModalArrayList;
    }

    public ArrayList<Recipe> getAllRecipeByCategoryId(String _id) {
        Cursor cursorCourses = database.rawQuery("SELECT rc."
                + RECIPE_ID + ", rc."
                + RECIPE_NAME + ", rc."
                + RECIPE_DES+ ", rc."
                + RECIPE_MATERIAL+ ", rc."
                + RECIPE_MAKING + ", rc."
                + RECIPE_AVATAR + " FROM "+ TBL_RECIPE + " rc" +
                " INNER JOIN " + TBL_CATEGORY_RECIPE + " cr ON cr." + RECIPE_ID + " = rc." + RECIPE_ID +
                " INNER JOIN " + TBL_CATEGORY + " ca ON ca." + CATEGORY_ID + " = " + "cr." + CATEGORY_ID +
                " WHERE ca." + CATEGORY_ID + " = " + _id, null);
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


    /*
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
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),
                        cursorCourses.getString(5)));
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return courseModalArrayList;
    }*/
/*
    public void delete(String _id){
        database.delete(MyDB.TBL_RECIPE,
                MyDB.RECIPE_ID + " = " + _id,
                null);
    }*/

}
