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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.letscook.db.MyDB;
import com.example.letscook.model.Note;
import com.example.letscook.model.Recipe;
import com.example.letscook.model.Wishlist;

import java.util.ArrayList;

public class WishlistDAO {
    private MyDB dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public WishlistDAO(Context c){
        context = c;
    }
    public WishlistDAO open() throws SQLException {
        dbHelper = new MyDB(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }

    public ArrayList<Recipe> getAllWishlist(String _id) {
        Cursor cursorCourses = database.rawQuery("SELECT ct." + RECIPE_ID + ", ct." + RECIPE_NAME + ", ct." + RECIPE_DES+ ", ct." + RECIPE_MATERIAL+ ", ct." + RECIPE_MAKING + ", ct." + RECIPE_AVATAR + " \n " +
                " From "+ TBL_USER + " us " + " \n " +
                " INNER JOIN " + TBL_WISHLIST + " wl ON wl." + USER_ID + " = us." +USER_ID + " \n " +
                " INNER JOIN " + TBL_RECIPE + " ct on ct." + RECIPE_ID + " = wl." + RECIPE_ID + " \n " +
                " where us." + USER_ID + " = " + _id, null);

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

    public void delete(String _id_recipe, String _id_user){
        database.delete(TBL_WISHLIST,
                USER_ID + " = " + _id_user + " AND " + RECIPE_ID + " = " + _id_recipe ,
                null);
    }

//    public void insert(String _id_recipe, String _id_user){
//        database.insert(TBL_WISHLIST,"( " + USER_ID + " , "+ RECIPE_ID + " )\n values ( " + _id_user + " , " + _id_recipe + " ) "  , null );
//    }
    public void insertWishlist(Wishlist wishlist){
        ContentValues contentValues =new ContentValues();
        contentValues.put(USER_ID,wishlist.getUserId());
        contentValues.put(RECIPE_ID,wishlist.getRecipeID());
        database.insert(TBL_WISHLIST, null,contentValues);
    }
}
