package com.example.letscook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDB extends SQLiteOpenHelper {

    private Context context;
    private  static final String DATABASE_NAME = "LetCook.sqlite";
    private  static final int DATABASE_VERSION = 1;

    private  static final String TBL_NOTE = "note";
    private  static final String NOTE_ID = "note_id";
    private  static final String NOTE_NAME = "note_name";
    private  static final String NOTE_CONTENT = "note_contnt";
    private  static final String FIRT_REGISTER_PTTM= "frstRegistPttm";

    private String TBL_CREATE_NOTE = " create table " + TBL_NOTE + " (" +
            NOTE_ID + " integer primary key ," +
            USER_ID + "INTEGER ," +
            NOTE_NAME + " text UNIQUE ," +
            NOTE_CONTENT + " text ," +
            FIRT_REGISTER_PTTM + " text ,"+
            " FOREIGN KEY(" + USER_ID + ") REFERENCES "+ TBL_USER + "(" + USER_ID + "))";

    private static final String TBL_USER = "user";
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String USER_AVATAR = "user_avatar";
    private static final String DATE_OF_BIRTH = "date_of_birth";
    private static final String JOB = "job";
    private static final String USER_DESCRIPTION = "user_description";

    private String TBL_CREATE_USER = " create table " + TBL_USER + " (" +
            USER_ID + " integer primary key AUTOINCREMENT ," +
            USER_NAME + " TEXT NOT NULL ," +
            EMAIL + " TEXT NOT NULL UNIQUE ," +
            PASSWORD + "TEXT NOT NULL , " +
            USER_AVATAR + " TEXT ," +
            DATE_OF_BIRTH + " text ,"+
            JOB + " TEXT ," +
            USER_DESCRIPTION + " TEXT )";

    private static final String TBL_WISHLIST = "wishlist";
    private static final String WISHLIST_ID = "wishlist_id";

    private String TBL_CREATE_WISHLIST = "create table " + TBL_WISHLIST + " (" +
            WISHLIST_ID + " integer primary key AUTOINCREMENT," +
            USER_ID + " INTEGER UNIQUE,"+
            RECIPE_ID + " INTEGER UNIQUE," +
            " FOREIGN KEY(" + RECIPE_ID + ") REFERENCES "+ TBL_RECIPE + "(" + RECIPE_ID + "),"+
            " FOREIGN KEY(" + USER_ID + ") REFERENCES "+ TBL_USER + "(" + USER_ID + "))";

    private static final String TBL_RECIPE = "recipe";
    private static final String RECIPE_ID = "recipe_id";
    private static final String RECIPE_NAME = "recipe_name";
    private static final String RECIPE_DETAIL = "recipe_detail";
    private static final String RECIPE_AVATAR = "recipe_avatar";

    private String TBL_CREATE_RECIPE= "create table " + TBL_RECIPE + " (" +
            RECIPE_ID + " integer primary key AUTOINCREMENT," +
            RECIPE_NAME + " TEXT NOT NULL ,"+
            RECIPE_DETAIL + " text NOT NULL ,"+
            RECIPE_AVATAR + " TEXT NOT NULL )";

    private static final String TBL_THEME = "theme";
    private static final String THEME_ID = "theme_id";
    private static final String THEME_NAME = "theme_name";

    private String TBL_CREATE_THEME= "create table " + TBL_THEME + " (" +
            THEME_ID + " integer primary key AUTOINCREMENT," +
            THEME_NAME + " TEXT UNIQUE )";

    private static final String TBL_THEME_RECIPE = "theme_recipe";

    private String TBL_CREATE_THEME_RECIPE= "create table " + TBL_THEME_RECIPE + " (" +
            THEME_ID + " integer primary key," +
            RECIPE_ID + " integer primary key)";

    private static final String TBL_CATEGORY = "category";
    private static final String CATEGORY_ID = "category_id";
    private static final String CATEGORY_NAME = "category_name";

    private String TBL_CREATE_CATEGORY= "create table " + TBL_CATEGORY + " (" +
            CATEGORY_ID + " integer primary key AUTOINCREMENT," +
            CATEGORY_NAME + " TEXT UNIQUE)";

    private static final String TBL_CATEGORY_RECIPE = "category_recipe";

    private String TBL_CREATE_CATEGORY_RECIPE= "create table " + TBL_CATEGORY_RECIPE + " (" +
            CATEGORY_ID + " integer primary key ," +
            RECIPE_ID + " integer primary key)";

    public MyDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
    public SQLiteDatabase getReadableDatabase() {
        throw new RuntimeException("Stub!");
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_NOTE);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_USER);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_WISHLIST);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_RECIPE);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_THEME_RECIPE);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_THEME);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_CATEGORY);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS  " + TBL_CATEGORY_RECIPE);
        onCreate(sqLiteDatabase);
    }
}
