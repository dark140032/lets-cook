package com.example.letscook.DAO;

import static com.example.letscook.db.MyDB.USER_ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.letscook.db.MyDB;
import com.example.letscook.model.Note;
import com.example.letscook.model.User;

import java.util.ArrayList;

public class UserDAO {
    private MyDB dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public UserDAO(Context c){
        context = c;
    }

    public UserDAO open() throws SQLException {
        dbHelper = new MyDB(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }

    //Get tất cả user
    public ArrayList<User> getAllUser() {
        Cursor cursorCourses = database.rawQuery("SELECT * FROM " + MyDB.TBL_USER, null);
        ArrayList<User> courseModalArrayList = new ArrayList<>();
        if (cursorCourses.moveToFirst()) {
            do {
                courseModalArrayList.add(new User(
                        cursorCourses.getString(0),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(4),
                        cursorCourses.getString(5),
                        cursorCourses.getString(6),
                        cursorCourses.getString(7)));
            } while (cursorCourses.moveToNext());
        }
        cursorCourses.close();
        return courseModalArrayList;
    }

    public User getUser(String email, String password) {
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + MyDB.TBL_USER + " where " + MyDB.EMAIL + " = '" + email + "' and " + MyDB.PASSWORD + " = '" + password + "'", null);
            User user = null;
            if (cursor != null) {
                cursor.moveToFirst();
                user = new User(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7));
            }
            return user;
        }catch (Exception ex){

        }
        return null;
    }

    public User getUserById(String userId) {
        try {

            Cursor cursor = database.query(MyDB.TBL_USER, null, MyDB.USER_ID + " = ?", new String[] { String.valueOf(userId) },null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                return  new User(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7));
            }
        }catch (Exception ex){

        }
        return null;
    }

    //Đăng ký tài khoản user
    public boolean register(User user){
        try {
            ContentValues values = new ContentValues();
            values.put(MyDB.USER_NAME, user.getUsername());
            values.put(MyDB.EMAIL, user.getEmail());
            values.put(MyDB.PASSWORD, user.getPassword());
            database.insert(MyDB.TBL_USER, null, values);
            return true;
        }catch (Exception ex){

        }
        return false;
    }

    //Change password
    public boolean updatePassword(String userId, String password) {
        try{
            ContentValues values = new ContentValues();
            values.put(MyDB.PASSWORD , password);
            database.update(MyDB.TBL_USER, values, USER_ID + " = ?", new String[] { String.valueOf(userId) });
            database.close();
            return true;
        }catch (Exception ex){
        }
        return false;
    }

<<<<<<< HEAD
    //Update thông tin profile
    public boolean updateProfile(User user) {
            ContentValues values = new ContentValues();
            values.put(MyDB.USER_NAME , user.getUsername());
            values.put(MyDB.USER_AVATAR , user.getUserAvatar());
            values.put(MyDB.USER_DESCRIPTION , user.getUserDescription());
            values.put(MyDB.JOB , user.getJob());
            values.put(MyDB.DATE_OF_BIRTH , user.getDateOfBirth());
            database.update(MyDB.TBL_USER, values, MyDB.USER_ID + " = ? ", new String[] { String.valueOf(user.getUserId()) });
            database.close();
            return true;
    }

    //Check mail is exist
    public boolean isExistMail(String email) {
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM " + MyDB.TBL_USER + " where " + MyDB.EMAIL + " = '" + email + "' ", null);
            if (cursor != null) {
                cursor.moveToFirst();
                if (cursor.getString(0).toString() != null) {
                    return true;
                }
            }
=======
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
>>>>>>> 066656332b58d003bf9a4801fe00e1aac4262304
        }catch (Exception ex){

        }
        return false;
    }
<<<<<<< HEAD
=======

>>>>>>> 066656332b58d003bf9a4801fe00e1aac4262304
}
