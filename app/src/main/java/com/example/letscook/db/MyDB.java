package com.example.letscook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDB extends SQLiteOpenHelper {

    private Context context;
    private  static final String DATABASE_NAME = "LetCook.sqlite";
    private  static final int DATABASE_VERSION = 1;

    public static final String TBL_NOTE = "note";
    public  static final String NOTE_ID = "note_id";
    public  static final String NOTE_NAME = "note_name";
    public  static final String NOTE_CONTENT = "note_contnt";
    public  static final String FIRT_REGISTER_PTTM= "frstRegistPttm";

    public String TBL_CREATE_NOTE = " create table " + TBL_NOTE + " (" +
            NOTE_ID + " integer primary key ," +
            NOTE_NAME + " text UNIQUE ," +
            NOTE_CONTENT + " text ," +
            FIRT_REGISTER_PTTM + " text ,"+
            USER_ID + " INTEGER ," +
            " FOREIGN KEY(" + USER_ID + ") REFERENCES "+ TBL_USER + "(" + USER_ID + "))";

    public static final String TBL_USER = "user";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String USER_AVATAR = "user_avatar";
    public static final String DATE_OF_BIRTH = "date_of_birth";
    public static final String JOB = "job";
    public static final String USER_DESCRIPTION = "user_description";

    public String TBL_CREATE_USER = " create table " + TBL_USER + " (" +
            USER_ID + " integer primary key AUTOINCREMENT ," +
            USER_NAME + " TEXT NOT NULL ," +
            EMAIL + " TEXT NOT NULL UNIQUE ," +
            PASSWORD + "TEXT NOT NULL , " +
            USER_AVATAR + " TEXT ," +
            DATE_OF_BIRTH + " text ,"+
            JOB + " TEXT ," +
            USER_DESCRIPTION + " TEXT )";

    public static final String TBL_WISHLIST = "wishlist";
    public static final String WISHLIST_ID = "wishlist_id";

    public String TBL_CREATE_WISHLIST = "create table " + TBL_WISHLIST + " (" +
            WISHLIST_ID + " integer primary key AUTOINCREMENT," +
            USER_ID + " INTEGER UNIQUE,"+
            RECIPE_ID + " INTEGER UNIQUE," +
            " FOREIGN KEY(" + RECIPE_ID + ") REFERENCES "+ TBL_RECIPE + "(" + RECIPE_ID + "),"+
            " FOREIGN KEY(" + USER_ID + ") REFERENCES "+ TBL_USER + "(" + USER_ID + "))";

    public static final String TBL_RECIPE = "recipe";
    public static final String RECIPE_ID = "recipe_id";
    public static final String RECIPE_NAME = "recipe_name";
    public static final String RECIPE_DETAIL = "recipe_detail";
    public static final String RECIPE_AVATAR = "recipe_avatar";

    public String TBL_CREATE_RECIPE= "create table " + TBL_RECIPE + " (" +
            RECIPE_ID + " integer primary key AUTOINCREMENT," +
            RECIPE_NAME + " TEXT NOT NULL ,"+
            RECIPE_DETAIL + " text NOT NULL ,"+
            RECIPE_AVATAR + " TEXT NOT NULL )";

    public static final String TBL_THEME = "theme";
    public static final String THEME_ID = "theme_id";
    public static final String THEME_NAME = "theme_name";

    public String TBL_CREATE_THEME= "create table " + TBL_THEME + " (" +
            THEME_ID + " integer primary key AUTOINCREMENT," +
            THEME_NAME + " TEXT UNIQUE )";

    public static final String TBL_THEME_RECIPE = "theme_recipe";

    public String TBL_CREATE_THEME_RECIPE= "create table " + TBL_THEME_RECIPE + " (" +
            THEME_ID + " integer, " +
            RECIPE_ID + " integer, "+
            " CONSTRAINT " +  TBL_THEME_RECIPE + " PRIMARY KEY (" + THEME_ID+ "," + RECIPE_ID +  "))";

    public static final String TBL_CATEGORY = "category";
    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_NAME = "category_name";

    public String TBL_CREATE_CATEGORY= "create table " + TBL_CATEGORY + " (" +
            CATEGORY_ID + " integer primary key AUTOINCREMENT," +
            CATEGORY_NAME + " TEXT UNIQUE)";

    public static final String TBL_CATEGORY_RECIPE = "category_recipe";

    public String TBL_CREATE_CATEGORY_RECIPE= "create table " + TBL_CATEGORY_RECIPE + " (" +
            CATEGORY_ID + " integer ," +
            RECIPE_ID + " integer ,"+
            " CONSTRAINT " +  TBL_CATEGORY_RECIPE + " PRIMARY KEY (" + CATEGORY_ID+ "," + RECIPE_ID +  "))";

    public MyDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("okie", "MyDB:" );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TBL_CREATE_NOTE);
        sqLiteDatabase.execSQL(TBL_CREATE_USER);
        sqLiteDatabase.execSQL(TBL_CREATE_WISHLIST);
        sqLiteDatabase.execSQL(TBL_CREATE_RECIPE);
        sqLiteDatabase.execSQL(TBL_CREATE_THEME_RECIPE);
        sqLiteDatabase.execSQL(TBL_CREATE_THEME);
        sqLiteDatabase.execSQL(TBL_CREATE_CATEGORY);
        sqLiteDatabase.execSQL(TBL_CREATE_CATEGORY_RECIPE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_NOTE);
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_USER);
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_WISHLIST);
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_RECIPE);
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_THEME_RECIPE);
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_THEME);
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_CATEGORY);
//        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_CATEGORY_RECIPE);
//        onCreate(sqLiteDatabase);
    }
}
