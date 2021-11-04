package com.example.letscook.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.letscook.db.MyDB;
import com.example.letscook.model.User;

import java.util.ArrayList;

public class UserDAO {
    private MyDB dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public UserDAO(Context c){
        context = c;
    }
    public  UserDAO open() throws SQLException {
        dbHelper = new MyDB(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User>  userList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM user";
        Cursor cursor = database.rawQuery(sqlQuery, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            User user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                                    cursor.getString(3), cursor.getString(4), cursor.getString(5),
                                    cursor.getString(6), cursor.getString(7));
            userList.add(user);
            cursor.moveToNext();
        }
        return userList;
    }
    public User getUser(String email, String password ) {

            Cursor cursor = database.query("user", null, "email = ?", new String[]{String.valueOf(email)}, null, null, null);
            Log.e("AAAAA",cursor.getString(2));
            if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
                if (password.equals(cursor.getString(3))) ;
                return new User(cursor.getString(0), cursor.getString(1), cursor.getString(2),
                        cursor.getString(3), cursor.getString(4), cursor.getString(5),
                        cursor.getString(6), cursor.getString(7));
            }
            return null;
    }

    public boolean updateProfile(User user) {
        try{
            ContentValues values = new ContentValues();
            values.put("user_name", user.getUsername());
            values.put("email", user.getEmail());
            values.put("password", user.getPassword());
            values.put("user_avatar", user.getUserAvatar());
            values.put("date_of_birth", user.getDateOfBirth());
            values.put("job", user.getJob());
            database.update("user", values,  "email = ?", new String[] { String.valueOf(user.getEmail()) });
            database.close();
            return true;
        }catch (Exception ex){

        }
        return false;
    }

    public boolean registerUser(User user) {
        try {
            open();
            ContentValues values = new ContentValues();
            values.put("user_id", user.getUserId());
            values.put("user_name", user.getUsername());
            values.put("email", user.getEmail());
            values.put("password", user.getPassword());
            values.put("user_avatar", user.getUserAvatar());
            values.put("date_of_birth", user.getDateOfBirth());
            values.put("job", user.getJob());
            values.put("user_description", user.getUserDescription());

            database.insert("user", null, values);
            close();
            return true;
        }catch (Exception ex){

        }
        return false;
    }

}
